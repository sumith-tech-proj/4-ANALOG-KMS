package obuits;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import static obuits.clsDefines.COMPANY_NAME_NMEA_PROT;
import static obuits.clsDefines.COMP_AMINEX;
import static obuits.clsDefines.DEFAULT_MAP_LATITIDE;
import static obuits.clsDefines.main_route_filepath;
import static obuits.clsDefines.route_filepath;
import static obuits.clsSharedVariables.objMapData;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.Tile;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.TileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.AbstractOsmTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.BingAerialTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.MapQuestOsmTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;

public class PanEmergencyMap extends javax.swing.JPanel {

    public static String api_key_cycle = "bee21445edbe44e788f916f0312022bc";

    Timer timer;
    TimerTask timerTask;
    JMapViewer map;
    Image image = null;
    boolean map_ld_success = false;
    TileSource tileSource;
    JComboBox tileSourceSelector;
    Tile mapTile = null;
    mapEmergency mapEmer;
    int refresh_map_time = 10;

    public PanEmergencyMap() {
        initComponents();
        setSize(600, 100);
        Coordinate coordinates[];
        List<Coordinate> route;
        //https://tile.thunderforest.com/cycle/{z}/{x}/{y}.png?apikey=bee21445edbe44e788f916f0312022bc
        int j;
        refresh_map_time = this.read_emergency_refresh_time_file();

        map = new JMapViewer() {
            boolean loaded = false;

            @Override
            public void tileLoadingFinished(Tile tile, boolean success) {
                super.tileLoadingFinished(tile, success);
                mapTile = tile;
                if (!loaded && success) {// if ( !loaded & success) {
                    loaded = true;

                } else if (tile.hasError()) {
                    map_ld_success = false;

                } else if (tile.isLoading()) {
                    map_ld_success = true;

                } else if (!success) {

                } else if (loaded && success) {
                    map_ld_success = true;

                } else {

                }

                repaint();
            }

            @Override
            public void zoomIn() {
                super.zoomIn();
                repaint();
            }

        };

        mapEmer = new mapEmergency(map);
        tileSource = ((TileSource) new CycleMap() {
            @Override
            protected String getApiKey() {
                return "bee21445edbe44e788f916f0312022bc";
            }
        });
        map.setTileSource(tileSource);

        tileSourceSelector = new JComboBox(new TileSource[]{new OsmTileSource.Mapnik(),
            tileSource, new BingAerialTileSource(), new MapQuestOsmTileSource()});

        tileSourceSelector.addItemListener((ItemEvent e) -> {
            map.setTileSource((TileSource) e.getItem());
        });

        panMapTop = new JPanel();

        javax.swing.JButton btnRefresh = new javax.swing.JButton();
        btnRefresh.setFont(btnRefresh.getFont().deriveFont(btnRefresh.getFont().getStyle() | java.awt.Font.BOLD, btnRefresh.getFont().getSize() + 7));

        btnRefresh.setText("REFRESH MAP");
        btnRefresh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRefresh.setPreferredSize(new java.awt.Dimension(150, 40));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }

            private void btnRefreshActionPerformed(ActionEvent evt) {
                map_refresh();
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        //http://www.yournavigation.org/api/1.0/gosmore.php?format=kml&flat=17.430167&flon=78.541717&tlat=17.400108&tlon=78.558280&v=motorcar&fast=1&layer=mapnik
        setLayout(new BorderLayout());

        try {
            // File image2 = new File(getClass().getResource("/Images/arrow_right.png").getFile()); 
            image = ImageIO.read(getClass().getResource("/Images/arrow_right.png"));//image2); 

            route = new ArrayList<>();

            // clsSharedVariables.no_maps_latlong_points=0;
            coordinates = new Coordinate[clsSharedVariables.no_maps_latlong_points];

            for (j = 0; j < clsSharedVariables.no_maps_latlong_points; j++) {
                coordinates[j] = new Coordinate(objMapData[j].latitude, objMapData[j].longitude);

                map.addMapMarker((new IconMarker(coordinates[j], j + 1, image)));
                route.add(coordinates[j]);
            }
            if (clsSharedVariables.no_maps_latlong_points > 0) {
                map.setDisplayPosition(coordinates[0], 15);
            } else {
                coordinates = new Coordinate[1];
                coordinates[0] = new Coordinate(DEFAULT_MAP_LATITIDE, clsDefines.DEFAULT_MAP_LONGITUDE);
                map.setDisplayPosition(coordinates[0], 10);
            }

            MapPolyLine polyLine = new MapPolyLine(route);
            map.addMapPolygon(polyLine);

        } catch (Exception e) {

        }
        try {
            image = ImageIO.read(getClass().getResource("/Images/bus.png"));
        } catch (Exception ex) {
            //Logger.getLogger(PanEmergencyMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        add(map, BorderLayout.CENTER);
        SpinnerModel value
                = new SpinnerNumberModel(refresh_map_time, //initial value  
                        5, //minimum value  
                        500, //maximum value  
                        5); //step  
        JSpinner spinner = new JSpinner(value);
        spinner.setFont(btnRefresh.getFont().deriveFont(spinner.getFont().getStyle() | java.awt.Font.BOLD, spinner.getFont().getSize() + 7));

        spinner.setPreferredSize(new java.awt.Dimension(100, 40));
        // spinner.setBounds(100, 100, 50, 30);
        spinner.addChangeListener((ChangeEvent e) -> {
            refresh_map_time = (int) spinner.getValue();
            write_emergency_refresh_time_file(refresh_map_time);
            stopTimer();
            startTimer();
        });

        JPanel pan = new JPanel();
        pan.add(spinner, BorderLayout.EAST);
        pan.add(btnRefresh, BorderLayout.WEST);

        add(pan, BorderLayout.SOUTH);

        //  add(btnRefresh, BorderLayout.SOUTH);
        //   add(  tileSourceSelector ,BorderLayout.SOUTH);
        //   add(panBottom, BorderLayout.SOUTH);
        startTimer();

        coordinates = null;

        route = null;
    }

    public class mapEmergency extends DefaultMapController {

        boolean loaded = false;

        public mapEmergency(JMapViewer lmap) {
            super(lmap);
            map = lmap;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.

        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e); //usTo change body of generated methods, choose Tools | Templates.
            //   map.zoomIn(e.getPoint());

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e); //usTo change body of generated methods, choose Tools | Templates.
            //   map.zoomIn(e.getPoint());

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            super.mouseWheelMoved(e); //usTo change body of generated methods, choose Tools | Templates.
            //   map.zoomIn(e.getPoint());

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e); //usTo change body of generated methods, choose Tools | Templates.
            //   map.zoomIn(e.getPoint());

        }

    }

    public void map_refresh() {
        Coordinate coordinates[];

        List<Coordinate> route;

        int j = 0;
        int retry_inc = 0;
        map.moveMap(map.getX(), map.getY());
        clsReadFiles objReadFiles = new clsReadFiles();
        if (clsSharedVariables.getNoEmergencyPtsFromGprs() == 2) {
            if (clsSharedVariables.getCurLatitude() == 0.0 || clsSharedVariables.getCurLongitude() == 0.0) {
                if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {

                    for (retry_inc = 0; retry_inc < 3; retry_inc++) {
                        if (objReadFiles.read_destination_map_data_google(clsSharedVariables.getEmergencySrcLat(), clsSharedVariables.getEmergencySrcLong(), clsSharedVariables.getEmergencyDesLat(), clsSharedVariables.getEmergencyDesLong()) == true) {
                            break;
                        }
                    }
                } else {
                    objReadFiles.read_destination_map_data(clsSharedVariables.getEmergencySrcLat(), clsSharedVariables.getEmergencySrcLong(), clsSharedVariables.getEmergencyDesLat(), clsSharedVariables.getEmergencyDesLong());
                }
            } else {
                if (COMPANY_NAME_NMEA_PROT == COMP_AMINEX) {
                    for (retry_inc = 0; retry_inc < 3; retry_inc++) {
                        if (objReadFiles.read_destination_map_data_google(clsSharedVariables.getCurLatitude(), clsSharedVariables.getCurLongitude(), clsSharedVariables.getEmergencyDesLat(), clsSharedVariables.getEmergencyDesLong()) == true) {
                            break;
                        }
                    }
                } else {
                    objReadFiles.read_destination_map_data(clsSharedVariables.getCurLatitude(), clsSharedVariables.getCurLongitude(), clsSharedVariables.getEmergencyDesLat(), clsSharedVariables.getEmergencyDesLong());
                }
            }
        }

        objReadFiles = null;
        try {
            // File image2 = new File(getClass().getResource("/Images/arrow_right.png").getFile()); 
            Image image2 = ImageIO.read(getClass().getResource("/Images/arrow_right.png"));//image2); 

            route = new ArrayList<>();

            coordinates = new Coordinate[clsSharedVariables.no_maps_latlong_points];

            map.removeAllMapMarkers();
            map.removeAllMapPolygons();
            for (j = 0; j < clsSharedVariables.no_maps_latlong_points; j++) {
                coordinates[j] = new Coordinate(objMapData[j].latitude, objMapData[j].longitude);

                map.addMapMarker((new IconMarker(coordinates[j], j + 1, image2)));
                route.add(coordinates[j]);
            }
            if (clsSharedVariables.no_maps_latlong_points > 0) {
                map.setDisplayPosition(coordinates[0], 15);
            } else {
                coordinates = new Coordinate[1];
                coordinates[0] = new Coordinate(DEFAULT_MAP_LATITIDE, clsDefines.DEFAULT_MAP_LONGITUDE);
                map.setDisplayPosition(coordinates[0], 10);
            }

            MapPolyLine polyLine = new MapPolyLine(route);
            map.addMapPolygon(polyLine);

            map.repaint();
            map.updateUI();
            polyLine = null;
            route = null;
            image2 = null;
            coordinates = null;
            polyLine = null;
        } catch (Exception e) {
        }
    }

    public final void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 1000, refresh_map_time * 1000);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        // Remove internal "registered things"
        stopTimer();
        map.removeAll();
        map = null;
    }

    private void stopTimer() {
        try {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            if (timerTask != null) {
                timerTask.cancel();
                timerTask = null;
            }
        } catch (Exception ex) {

        }
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {

            IconMarkerImg oldMarker = null;
            IconMarkerImg newMarker = null;
            boolean map_zoomed = false;
            double lat;
            double longi;
            Coordinate cord;
            int tim_inc = 0;

            MouseEvent mec = new MouseEvent(map, // which
                    MouseEvent.MOUSE_CLICKED, // what
                    System.currentTimeMillis(), // when
                    0, // no modifiers
                    10, 10, // where: at (10, 10}
                    1, // only 1 click
                    false); // not a popup trigger

            MouseEvent mep = new MouseEvent(map, // which
                    MouseEvent.MOUSE_PRESSED, // what
                    System.currentTimeMillis(), // when //System.currentTimeMillis()
                    0, // no modifiers
                    10, 10, // where: at (10, 10}
                    1, // only 1 click
                    false); // not a popup trigger

            MouseEvent med = new MouseEvent(map, // which
                    MouseEvent.MOUSE_DRAGGED, // what
                    System.currentTimeMillis(), // when //System.currentTimeMillis()
                    0, // no modifiers
                    10, 10, // where: at (10, 10}
                    1, // only 1 click
                    false); // not a popup trigger

            MouseEvent mer = new MouseEvent(map, // which
                    MouseEvent.MOUSE_RELEASED, // what
                    System.currentTimeMillis(), // when //System.currentTimeMillis()
                    0, // no modifiers
                    10, 10, // where: at (10, 10}
                    1, // only 1 click
                    false); // not a popup trigger

            /*
             Component source, int id, long when, int modifiers,
             int x, int y, int clickCount, boolean popupTrigger,
             int scrollType, int scrollAmount, int wheelRotatio
             */
            MouseWheelEvent mwmr = new MouseWheelEvent(map, // which
                    MouseWheelEvent.MOUSE_WHEEL, // what
                    System.currentTimeMillis(), // when //System.currentTimeMillis()
                    0, // no modifiers
                    10, 10, // where: at (10, 10}
                    1, // only 1 click
                    false,//popupTrigger
                    1, //scrollType
                    2, //scrollAmount
                    1); // wheelRotatio

            int zoom_val = 14;

            @Override
            public void run() {
                //  if (!map_ld_success)
                {

                    if (mapTile == null) {

                    } else if (mapTile.hasError()) {
                        try {

                            map.dispatchEvent(mep);
                            Thread.sleep(10);
                            map.dispatchEvent(mer);
                            Thread.sleep(10);
                            map.dispatchEvent(mec);
                            Thread.sleep(10);
                            map.dispatchEvent(mer);
                            Thread.sleep(10);
                            map.dispatchEvent(mec);
                            Thread.sleep(10);
                            map.dispatchEvent(mep);
                            // map.dispatchEvent(mer);
                            Thread.sleep(10);
                            map.dispatchEvent(mec);
                            Thread.sleep(10);
                            // map.dispatchEvent(med);
                            //  Thread.sleep(10);
                            //  map.dispatchEvent(mer);

                            if (map_zoomed) {
                                map_zoomed = false;
                                zoom_val = map.getZoom();

                                // map.dispatchEvent(mwmr);
                                Thread.sleep(10);

                                map.zoomIn();
                                Thread.sleep(10);
                                map.zoomOut();
                                Thread.sleep(10);

                            } else {
                                zoom_val = map.getZoom();
                                // map.dispatchEvent(mwmr);
                                Thread.sleep(10);
                                map.zoomOut();
                                Thread.sleep(10);
                                map.zoomIn();
                                Thread.sleep(10);

                                map_zoomed = true;

                            }

                            /*
                             Mouse Pressed
                             Mouse mouseReleased
                             Mouse Clicked
                             Mouse Pressed
                             Mouse mouseReleased
                             Mouse Clicked
                             */
                        } catch (InterruptedException ex) {

                        }

                    } else if (mapTile.isLoading()) {

                    } else if (mapTile.isLoaded()) {

                    }
                }
                try {

                    lat = clsSharedVariables.getCurLatitude();
                    longi = clsSharedVariables.getCurLongitude();
                    if (lat == 0.0 && clsSharedVariables.no_maps_latlong_points > 0) {
                        lat = objMapData[0].latitude;
                    }
                    if (longi == 0.0 && clsSharedVariables.no_maps_latlong_points > 0) {
                        longi = objMapData[0].longitude;
                    }
                    if (lat != 0.0 && longi != 0.0) {
                        cord = new Coordinate(lat, longi);
                        if (newMarker != null) {
                            oldMarker = newMarker;
                            newMarker = null;
                        }

                        newMarker = new IconMarkerImg(cord, image);
                        map.addMapMarker(newMarker);
                        //if (tim_inc++ > 20) {
                        // tim_inc = 0;
                        // if (tim_inc % 2 == 0) {
                        map.setDisplayPosition(cord, 16);
                        // }
                        // }
                        if (oldMarker != null) {
                            map.removeMapMarker(oldMarker);
                            oldMarker = null;

                        }
                        cord = null;

                    }
                } catch (Exception ex) {

                }
                if (tim_inc++ > 20) {
                    tim_inc = 0;

                    System.gc();
                }

                //  mapEmer.mouseClicked( new MouseEvent(trigger, 0, System.currentTimeMillis(), 1, 0, 0, 0, false));
                //  map.transferFocus();
                // tileSourceSelector.setSelectedIndex(0);
            }
        };
    }

    public class IconMarker extends MapMarkerCircle implements MapMarker {

        private Image image;
        int data;

        public IconMarker(Coordinate coord, Image image) {
            this(coord, 1, image);
        }

        public IconMarker(Coordinate coord, double radius, Image image) {
            //super(coord, radius);
            super(coord, 1.0);
            data = (int) radius;
            this.image = image;
        }

        @Override
        public void paint(Graphics g, Point position, int radio) {
            double r = this.getRadius();
            int width = (int) (this.image.getWidth(null) * r);
            int height = (int) (this.image.getHeight(null) * r);
            int w2 = width / 2;
            int h2 = height / 2;
            //  g.drawImage(this.image, position.x - w2, position.y - h2, width, height, null);
            g.setColor(Color.RED);
            g.fillOval(position.x - w2, position.y - h2, width, height);
            FontMetrics fm = g.getFontMetrics();
            String text = String.valueOf(data);
            double textWidth = fm.getStringBounds(text, g).getWidth();
            // What is the job of getstringbounds 
            g.setColor(Color.white);
            Font f = new Font("Serif", Font.BOLD, 24);
            g.setFont(null);
            // g.drawString("1", position.x - w2, position.y - h2 );
            g.drawString(text, (int) (position.x - textWidth / 2), (int) (position.y + fm.getMaxAscent() / 2));
            this.paintText(g, position);
        }

        public Image getImage() {
            return this.image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

    }

    public class IconMarkerImg extends MapMarkerCircle implements MapMarker {

        private Image image;

        public IconMarkerImg(Coordinate coord, Image image) {
            this(coord, 1, image);
        }

        public IconMarkerImg(Coordinate coord, double radius, Image image) {
            super(coord, radius);
            this.image = image;
        }

        @Override
        public void paint(Graphics g, Point position, int radio) {
            double r = this.getRadius();
            int width = (int) (this.image.getWidth(null) * r);
            int height = (int) (this.image.getHeight(null) * r);
            int w2 = width / 2;
            int h2 = height / 2;
            g.drawImage(this.image, position.x - w2, position.y - h2, width, height, null);
            this.paintText(g, position);
        }

        public Image getImage() {
            return this.image;
        }

        public void setImage(Image image) {
            this.image = image;
        }
    }

    public static class MapPolyLine extends MapPolygonImpl {

        public MapPolyLine(List<? extends ICoordinate> points) {
            super(null, null, points);
        }

        @Override
        public void paint(Graphics g, List<Point> points) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(getColor());
            g2d.setStroke(new BasicStroke(10));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // g2d.setStroke(getStroke());
            Path2D path = buildPath(points);
            g2d.draw(path);
            g2d.dispose();
        }

        private Path2D buildPath(List<Point> points) {
            Path2D path = new Path2D.Double();
            if (points != null && points.size() > 0) {
                Point firstPoint = points.get(0);
                path.moveTo(firstPoint.getX(), firstPoint.getY());
                for (Point p : points) {
                    path.lineTo(p.getX(), p.getY());
                }
            }
            return path;
        }
    }

    public abstract class CycleMap extends AbstractOsmTileSource {

        private static final String PATTERN = "https://%s.tile.thunderforest.com/cycle";

        private final String[] SERVER = {"a", "b", "c"};

        private int serverNum;

        /**
         * Constructs a new {@code CycleMap} tile source.
         */
        public CycleMap() {
            super("OpenCycleMap", PATTERN, "opencyclemap");
        }

        @Override
        public String getBaseUrl() {
            String url = String.format(this.baseUrl, new Object[]{SERVER[serverNum]});
            serverNum = (serverNum + 1) % SERVER.length;
            return url;
        }

        /**
         * Get the thunderforest API key.
         *
         * Needs to be registered at their web site.
         *
         * @return the API key
         */
        protected abstract String getApiKey();

        @Override
        public int getMaxZoom() {
            return 18;
        }

        @Override
        public String getTileUrl(int zoom, int tilex, int tiley) throws IOException {
            return this.getBaseUrl() + getTilePath(zoom, tilex, tiley) + "?apikey=" + getApiKey();
        }

        @Override
        public String getTermsOfUseText() {
            return "Maps © Thunderforest";
        }

        @Override
        public String getTermsOfUseURL() {
            return "https://thunderforest.com/terms/";
        }

    }

    public synchronized int read_emergency_refresh_time_file() {
        String line;
        BufferedReader br = null;

//Get the text file
        File file = new File(route_filepath, "emerrgencymap_refreshtime.txt");

        File f = new File(main_route_filepath, "emerrgencymap_refreshtime.txt");
        if (f.exists()) {
            try {
                br = new BufferedReader(new FileReader(f));
                while ((line = br.readLine()) != null) {
                    return Integer.parseInt(line);
                }

            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } catch (Exception e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }

                line = null;
                br = null;
                file = null;
                f = null;
            }

        } else if (file.exists() && clsSharedVariables.getHardDriveDetected()) {
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    return Integer.parseInt(line);
                }

            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } catch (Exception e) {
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {

                }

                line = null;
                br = null;
                file = null;

            }
        }
        return 10;
    }

    public synchronized void write_emergency_refresh_time_file(int refresh_map_time) {
        // Find the root of the external storage.
        // See http://developer.android.com/guide/topics/data/data-  storage.html#filesExternal
        //  StringBuilder tv=new StringBuilder();
        //  File root = android.os.Environment.getExternalStorageDirectory();
        //  tv.append("\nExternal file system root: "+root);
        // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder
        //File dir = new File(root.getAbsolutePath() + "/download");
        // dir.mkdirs();
        String path = route_filepath.getAbsolutePath();
        if (!clsSharedVariables.getHardDriveDetected()) {
            path = main_route_filepath.getAbsolutePath();
        }
        File file = new File(path, "emerrgencymap_refreshtime.txt");

        FileOutputStream f;
        PrintWriter pw;
        try {
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(refresh_map_time);
            pw.flush();
            pw.close();
            f.close();

        } catch (FileNotFoundException e) {

            // Log.i(TAG, "******* File not found. Did you" +
            //" add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {

        } finally {

            f = null;
            pw = null;
            file = null;

        }
        try {
            file = new File(main_route_filepath, "emerrgencymap_refreshtime.txt");
            f = new FileOutputStream(file, false);
            pw = new PrintWriter(f);
            pw.println(refresh_map_time);
            pw.flush();
            pw.close();
            f.close();
        } catch (Exception e) {

        } finally {

            f = null;
            pw = null;
            file = null;

        }
        //   tv.append("\n\nFile written to "+file);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panMapTop = new javax.swing.JPanel();

        javax.swing.GroupLayout panMapTopLayout = new javax.swing.GroupLayout(panMapTop);
        panMapTop.setLayout(panMapTopLayout);
        panMapTopLayout.setHorizontalGroup(
            panMapTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        panMapTopLayout.setVerticalGroup(
            panMapTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panMapTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(panMapTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panMapTop;
    // End of variables declaration//GEN-END:variables
}
