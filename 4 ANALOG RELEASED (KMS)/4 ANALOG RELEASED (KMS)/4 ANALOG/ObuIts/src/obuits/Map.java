/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static obuits.clsDefines.CUR_INC_AUDIO_STOP_STATE;
import static obuits.clsDefines.ENDSTOP_AUDIO_STOP_STATE;
import static obuits.clsDefines.LANG_ENGLISH;
import static obuits.clsDefines.TRIP_START;
import static obuits.clsSharedVariables.getCurLongitude;
import static obuits.clsSharedVariables.getCurSchNoTrips;
import static obuits.clsSharedVariables.getCurSchRouteNo;
import static obuits.clsSharedVariables.getCurStopNo;
import static obuits.clsSharedVariables.getCurTripNo;
import static obuits.clsSharedVariables.getCurTripStat;
import static obuits.clsSharedVariables.getGpsSpeed;
import static obuits.clsSharedVariables.getHarshAcc;
import static obuits.clsSharedVariables.getHarshBrk;
import static obuits.clsSharedVariables.getNoStopsRoute;
import static obuits.clsSharedVariables.getOverSpeed;
import static obuits.clsSharedVariables.getSchRouteEnable;
import static obuits.clsSharedVariables.getShowStopStopDis;
import static obuits.clsSharedVariables.get_driver_id;
import static obuits.clsSharedVariables.gps_data;
import static obuits.clsSharedVariables.lang_type;
import static obuits.clsSharedVariables.setCurActivity;
import static obuits.clsSharedVariables.setHarshAcc;
import static obuits.clsSharedVariables.setHarshBrk;
import static obuits.clsSharedVariables.setOverSpeed;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class Map extends javax.swing.JPanel {

    static String l_schid = "";
    static boolean route_deviate = false;
    String route_no = "";
    Double lat;
    Double longi;
    short l_cur_stop_no = 0;
    short l_cur_trip_no = 255;
    byte l_stop_state = clsDefines.STOP_STATE_NONE;
    int avg_speed;
    short prev_speed = 0;
    byte p_map_stop_state = clsDefines.NONE_AUDIO_STOP_STATE;
    byte state;
    short no_of_stops_in_a_route = getNoStopsRoute();
    DecimalFormat df = new DecimalFormat("#0.00");
    String str;
    Timer timer;
    TimerTask timerTask;
    final JMapViewer map;
    private static final long serialVersionUID = 1L;

    public Map(JPanel panMainPan, JPanel panCenterMainPan) {
        initComponents();
        setSize(400, 100);
        lat = clsSharedVariables.getCurLatitude();
        longi = getCurLongitude();
        if (lat == 0.0) {
            lat = clsDefines.DEFAULT_MAP_LATITIDE;
        }
        if (longi == 0.0) {
            longi = clsDefines.DEFAULT_MAP_LONGITUDE;
        }
        map = new JMapViewer();
        panMapTop = new JPanel();
        setLayout(new BorderLayout());
        Coordinate c = new Coordinate(lat, longi);
        map.setDisplayPosition(c, 15);
        add(map, BorderLayout.NORTH);

        add(panBottom, BorderLayout.SOUTH);

        startTimer();

        try {
            if (getCurStopNo() < no_of_stops_in_a_route) {
                if (getCurStopNo() > 0) {
                    str = "  " + clsSharedVariables.gps_data[getCurStopNo() - 1].stop_name + "\n  ";
                    if (getCurStopNo() + 1 < no_of_stops_in_a_route) {
                        str = "  " + clsSharedVariables.gps_data[getCurStopNo()].stop_name + "  "; // + clsSharedVariables.gps_data[getCurStopNo() + 1].reg1_stop_name ;
                    }

                } else {
                    str = "  " + clsSharedVariables.gps_data[getCurStopNo()].stop_name + "  ";

                    if (getCurStopNo() + 1 < no_of_stops_in_a_route) {
                        str = "  " + clsSharedVariables.gps_data[getCurStopNo() + 1].stop_name + "  ";
                    }
                }
                str = "SRC-DES : " + clsSharedVariables.gps_data[0].stop_name + " - " + clsSharedVariables.gps_data[getNoStopsRoute() - 1].stop_name;

            } else {
                txtCurStop.setText("");
                txtNextStop.setText("");
                txtDistance.setText("");
                txtEta.setText("");
                txtCurSpeed.setText("");
            }

            if (getCurTripNo() >= getCurSchNoTrips() && getSchRouteEnable() == clsDefines.SCHDEULE_ENABLE) {

                txtCurStop.setText("");
                txtNextStop.setText("");
                txtDistance.setText("");
                txtEta.setText("");
                txtCurSpeed.setText("");
            }

            setCurActivity(clsDefines.TRIP_ACTIVITY);
            try {
                clsReadFiles objReadFilesAct = new clsReadFiles();
                objReadFilesAct.write_activity_name(clsDefines.TRIP_ACTIVITY);
                objReadFilesAct = null;
            } catch (Exception ex) {

            }
            startTimer();
        } catch (Exception ex) {

        }
    }

    public final void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 1000, 1000);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        stopTimer();
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

    private void load_all_Stops() {
        try {

            int i = 0;
            short no_of_stops_in_a_route1 = getNoStopsRoute();
            byte cur_stop_no = getCurStopNo();

            final JLabel[] tv = new JLabel[no_of_stops_in_a_route1];

            for (i = 0; i < no_of_stops_in_a_route1; i++) {
                tv[i] = new JLabel();
                if (lang_type == LANG_ENGLISH) {
                    tv[i].setText("           " + gps_data[i].stop_name + "           ");
                } else {
                    tv[i].setText("           " + gps_data[i].reg1_stop_name + "           ");
                }
                Dimension d = new Dimension(250, 50);
                tv[i].setPreferredSize(d);

                tv[i].setFont(new java.awt.Font("Algerian", 1, 18)); // NOI18N
                tv[i].setForeground(new java.awt.Color(0, 102, 51));

                tv[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                tv[i].setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                tv[i].setVerticalTextPosition(javax.swing.SwingConstants.TOP);
            }

        } catch (Exception ex) {

        }
    }
    static int map_cnt_inc = 0;

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            @Override
            public void run() {
                //use a handler to run a toast that shows the current timestamp
                Coordinate c;
                short no_of_stops_in_a_route = getNoStopsRoute();
                try {

                    short cur_trip_no = getCurTripNo();
                    byte cur_stop_no = getCurStopNo();
                    lat = clsSharedVariables.getCurLatitude();
                    longi = getCurLongitude();

                    SwingUtilities.invokeLater(() -> {
                     double distanceKm = getShowStopStopDis() / 1000.0;
                     txtDistance.setText(df.format(distanceKm) + " km");
                     txtCurSpeed.setText(String.valueOf(getGpsSpeed()) + " Kmph");
                   });

                    c = new Coordinate(lat, longi);
                    map.addMapMarker(new MapMarkerDot(c));

                    if (map_cnt_inc++ > 10) {
                        map_cnt_inc = 0;
                        map.setDisplayPosition(c, 16);
                    }
                    c = null;
                    setHarshAcc(false);

                    if (!l_schid.equals(get_driver_id())) {
                        l_schid = get_driver_id();

                    }

                    if (getHarshAcc() == true) {
                        setHarshAcc(false);
                    }
                    if (getOverSpeed() == true) {
                        setOverSpeed(false);
                    }
                    if (getHarshBrk() == true) {
                        setHarshBrk(false);
                    }
                    if (getCurTripStat() == TRIP_START) {
                        state = clsSharedVariables.getStopState();
                        if (l_stop_state != state) {
                            l_stop_state = state;
                            if (state == clsDefines.STOP_STATE_IDENTIFYING) {

                                SwingUtilities.invokeLater(() -> {
                                    txtViewCurrentStop.setText("             "); //getResources().getText(R.string.identifyingStop));
                                    txtCurStop.setText(""); //clsSharedVariables.gps_data[cur_stop_no].stop_name);
                                    txtViewNextStop.setText("Next Stop");
                                });
                                if (cur_stop_no < no_of_stops_in_a_route) {
                                    SwingUtilities.invokeLater(() -> {
                                        if (lang_type == LANG_ENGLISH) {
                                            txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no].stop_name);
                                        } else {
                                            txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name);
                                        }
                                    });
                                } else {
                                    SwingUtilities.invokeLater(() -> {
                                        txtViewNextStop.setVisible(false);
                                    });
                                }
                                //  txtViewTripStatus.setText("");
                            }
                        } else if (state == clsDefines.CUR_STOP_STATE) {
                            SwingUtilities.invokeLater(() -> {
                                txtViewCurrentStop.setText("Current Stop");
                                txtViewNextStop.setText("Next stop");
                            });
                            if (lang_type == LANG_ENGLISH) {
                                SwingUtilities.invokeLater(() -> {
                                    txtCurStop.setText(clsSharedVariables.gps_data[cur_stop_no].stop_name);
                                    if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                                        txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no + 1].stop_name);
                                    } else {
                                        txtNextStop.setText(" ");
                                    }
                                });
                            } else {
                                SwingUtilities.invokeLater(() -> {
                                    txtCurStop.setText(clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name);
                                    if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                                        txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no + 1].reg1_stop_name);
                                    } else {
                                        txtNextStop.setText(" ");
                                    }
                                });
                            }

                            //  txtViewTripStatus.setText("");
                        } else if (state == clsDefines.NEXT_STOP_STATE) {
                            SwingUtilities.invokeLater(() -> {
                                txtViewCurrentStop.setText("Current Stop");
                                txtViewNextStop.setText("Next stop");
                            });
                            if (cur_stop_no > 0) {
                                SwingUtilities.invokeLater(() -> {
                                    if (lang_type == LANG_ENGLISH) {
                                        txtCurStop.setText(clsSharedVariables.gps_data[cur_stop_no - 1].stop_name);
                                        txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no].stop_name);
                                    } else {
                                        txtCurStop.setText(clsSharedVariables.gps_data[cur_stop_no - 1].reg1_stop_name);
                                        txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name);
                                    }
                                });
                            } else {
                                if (lang_type == LANG_ENGLISH) {
                                    SwingUtilities.invokeLater(() -> {
                                        txtCurStop.setText(clsSharedVariables.gps_data[cur_stop_no].stop_name);
                                        if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                                            txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no + 1].stop_name);
                                        } else {
                                            txtNextStop.setText("");
                                            txtNextStop.setVisible(false);

                                        }
                                    });
                                } else {
                                    SwingUtilities.invokeLater(() -> {
                                        txtCurStop.setText(clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name);
                                        if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                                            txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no + 1].reg1_stop_name);
                                        } else {
                                            txtNextStop.setText("");
                                            txtNextStop.setVisible(false);

                                        }
                                    });
                                }
                            }
                            // txtViewTripStatus.setText("");
                        } else if (state == clsDefines.ROUTE_DEVIATE_STATE) {
                            route_deviate = true;
                            SwingUtilities.invokeLater(() -> {
                                txtViewNextStop.setText("Route Deviated");
                            });
                        } else if (state == clsDefines.APP_STOP_STATE) {
                            if (cur_stop_no > 0) {
                                SwingUtilities.invokeLater(() -> {
                                    txtViewCurrentStop.setText("Previous Stop");
                                    txtViewNextStop.setText("Arriving stop");
                                    if (lang_type == LANG_ENGLISH) {
                                        txtCurStop.setText(clsSharedVariables.gps_data[cur_stop_no - 1].stop_name);

                                        txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no].stop_name);
                                    } else {
                                        txtCurStop.setText(clsSharedVariables.gps_data[cur_stop_no - 1].reg1_stop_name);

                                        txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name);
                                    }
                                });
                            } else {
                                SwingUtilities.invokeLater(() -> {
                                    txtViewCurrentStop.setText("Previous Stop");
                                    txtViewNextStop.setText("Arriving stop");

                                    if (lang_type == LANG_ENGLISH) {
                                        txtCurStop.setText(clsSharedVariables.gps_data[cur_stop_no].stop_name);
                                        txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no + 1].stop_name);
                                    } else {
                                        txtCurStop.setText(clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name);
                                        txtNextStop.setText(clsSharedVariables.gps_data[cur_stop_no + 1].reg1_stop_name);
                                    }
                                });
                            }

                        }

                        if (!route_no.equals(getCurSchRouteNo(cur_trip_no))) {
                            route_no = getCurSchRouteNo(cur_trip_no);
                        }
                        if (l_cur_stop_no != cur_stop_no && cur_stop_no > 0) {
                            //l_cur_stop_no = cur_stop_no;
                            if (route_deviate == false) {
                            }
                            route_deviate = false;
                        }
                        if (cur_trip_no != l_cur_trip_no) {

                            l_cur_trip_no = cur_trip_no;
                            load_all_Stops();
                        }
                        avg_speed = getGpsSpeed();
                        //get the current timeStamp
                        if (getGpsSpeed() != 0) {
                            // setmMapPolyLine(lat, longi, getGpsSpeed(),clsSharedVariables. getCurHeading());
                            if (getCurTripStat() == TRIP_START) {
                                int dur;
                                //   long sec = getCurSec();
                                double next_stop_distance = getShowStopStopDis();
                                dur = (int) (next_stop_distance * 18) / (avg_speed * 2 * 5);  //(formaula : time = ((distance * 18)/(speed *5) )
                                //dur = dur * 1000  ;
                                //  Calendar cal = Calendar.getInstance();
                                // cal.setTimeInMillis(sec + dur);
                                SwingUtilities.invokeLater(() -> {
                                    if (dur > 60) {
                                        txtEta.setText(dur / 60 + " min");
                                    } else {
                                        txtEta.setText(dur + " Sec");
                                    }
                                });
                            }
                        }
                        if (prev_speed != 0 && avg_speed == 0) {
                            prev_speed = (short) avg_speed;
                            SwingUtilities.invokeLater(() -> {
                                if (p_map_stop_state == clsDefines.CUR_AUDIO_STOP_STATE || p_map_stop_state == CUR_INC_AUDIO_STOP_STATE || p_map_stop_state == ENDSTOP_AUDIO_STOP_STATE) {
                                    //    imgRoadBus.setVisible(true);
                                } else if (p_map_stop_state == clsDefines.NEXT_AUDIO_STOP_STATE) {
                                    //    imgRoadBus2.setVisible(true);
                                } else if (p_map_stop_state == clsDefines.APP_AUDIO_STOP_STATE) {
                                    //       imgRoadBus3.setVisible(true);//noemal
                                }
                            });
                        } else if (prev_speed == 0 && avg_speed != 0) {
                            SwingUtilities.invokeLater(() -> {
                                prev_speed = (short) avg_speed;
                                if (p_map_stop_state == clsDefines.CUR_AUDIO_STOP_STATE || p_map_stop_state == CUR_INC_AUDIO_STOP_STATE || p_map_stop_state == ENDSTOP_AUDIO_STOP_STATE) {
                                    //     imgRoadBus.setVisible(true); //green
                                } else if (p_map_stop_state == clsDefines.NEXT_AUDIO_STOP_STATE) {
                                    //    imgRoadBus2.setVisible(true); //green
                                } else if (p_map_stop_state == clsDefines.APP_AUDIO_STOP_STATE) {
                                    //    imgRoadBus3.setVisible(true);//green
                                }
                            });
                        }

                    }
                } catch (Exception ex) {

                }

            }
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        panBottom = new javax.swing.JPanel();
        txtViewNextStop = new javax.swing.JLabel();
        txtViewCurrentStop = new javax.swing.JLabel();
        txtViewDistance = new javax.swing.JLabel();
        txtViewEta = new javax.swing.JLabel();
        txtCurStop = new javax.swing.JLabel();
        txtNextStop = new javax.swing.JLabel();
        txtDistance = new javax.swing.JLabel();
        txtEta = new javax.swing.JLabel();
        txtCurSpeed = new javax.swing.JLabel();
        txtViewSpeed = new javax.swing.JLabel();
        panMapTop = new javax.swing.JPanel();

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        panBottom.setBackground(new java.awt.Color(0, 0, 102));
        panBottom.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panBottom.setForeground(new java.awt.Color(255, 255, 255));
        panBottom.setPreferredSize(new java.awt.Dimension(600, 100));

        txtViewNextStop.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtViewNextStop.setForeground(new java.awt.Color(255, 255, 255));
        txtViewNextStop.setText("Next Stop");

        txtViewCurrentStop.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtViewCurrentStop.setForeground(new java.awt.Color(255, 255, 255));
        txtViewCurrentStop.setText("Current Stop");

        txtViewDistance.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtViewDistance.setForeground(new java.awt.Color(255, 255, 255));
        txtViewDistance.setText("Distance");

        txtViewEta.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtViewEta.setForeground(new java.awt.Color(255, 255, 255));
        txtViewEta.setText("ETA");

        txtCurStop.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtCurStop.setForeground(new java.awt.Color(255, 255, 255));
        txtCurStop.setText("Tarnaka");
        txtCurStop.setPreferredSize(new java.awt.Dimension(180, 20));

        txtNextStop.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNextStop.setForeground(new java.awt.Color(255, 255, 255));
        txtNextStop.setText("ECIL");
        txtNextStop.setPreferredSize(new java.awt.Dimension(180, 20));

        txtDistance.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtDistance.setForeground(new java.awt.Color(255, 255, 255));
        txtDistance.setText("100km");

        txtEta.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtEta.setForeground(new java.awt.Color(255, 255, 255));
        txtEta.setText("5 mins");

        txtCurSpeed.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtCurSpeed.setForeground(new java.awt.Color(255, 255, 255));
        txtCurSpeed.setText("0 KMPH");

        txtViewSpeed.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtViewSpeed.setForeground(new java.awt.Color(255, 255, 255));
        txtViewSpeed.setText("Speed");

        javax.swing.GroupLayout panBottomLayout = new javax.swing.GroupLayout(panBottom);
        panBottom.setLayout(panBottomLayout);
        panBottomLayout.setHorizontalGroup(
            panBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCurStop, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtViewCurrentStop, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panBottomLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtViewNextStop, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtViewDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(panBottomLayout.createSequentialGroup()
                        .addComponent(txtNextStop, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addGroup(panBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panBottomLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(txtEta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panBottomLayout.createSequentialGroup()
                        .addComponent(txtViewEta, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addGap(18, 18, 18)
                .addGroup(panBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCurSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtViewSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panBottomLayout.setVerticalGroup(
            panBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panBottomLayout.createSequentialGroup()
                        .addGroup(panBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewNextStop, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtViewCurrentStop, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtViewDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panBottomLayout.createSequentialGroup()
                        .addComponent(txtViewSpeed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(txtCurSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panBottomLayout.createSequentialGroup()
                        .addComponent(txtViewEta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(panBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCurStop, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNextStop, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(panBottom);

        javax.swing.GroupLayout panMapTopLayout = new javax.swing.GroupLayout(panMapTop);
        panMapTop.setLayout(panMapTopLayout);
        panMapTopLayout.setHorizontalGroup(
            panMapTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
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
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panMapTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel panBottom;
    private javax.swing.JPanel panMapTop;
    private javax.swing.JLabel txtCurSpeed;
    private javax.swing.JLabel txtCurStop;
    private javax.swing.JLabel txtDistance;
    private javax.swing.JLabel txtEta;
    private javax.swing.JLabel txtNextStop;
    private javax.swing.JLabel txtViewCurrentStop;
    private javax.swing.JLabel txtViewDistance;
    private javax.swing.JLabel txtViewEta;
    private javax.swing.JLabel txtViewNextStop;
    private javax.swing.JLabel txtViewSpeed;
    // End of variables declaration//GEN-END:variables
}
