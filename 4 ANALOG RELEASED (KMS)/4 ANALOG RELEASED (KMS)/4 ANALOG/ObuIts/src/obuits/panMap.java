package obuits;

import java.awt.Dimension;
import static obuits.clsDefines.CUR_INC_AUDIO_STOP_STATE;
import static obuits.clsDefines.ENDSTOP_AUDIO_STOP_STATE;
import static obuits.clsDefines.TRIP_START;
import static obuits.clsSharedVariables.*;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static obuits.clsDefines.LANG_ENGLISH;
import static obuits.clsDefines.LANG_HINDI;
import static obuits.clsDefines.LANG_REG;
import static obuits.clsSharedVariables.getCurTripNo;
import static obuits.clsSharedVariables.getNoStopsRoute;

public class panMap extends javax.swing.JPanel {

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
    DecimalFormat df = new DecimalFormat("#0.00");
    boolean all_stops = false;
    String str;
    Timer timer;
    TimerTask timerTask;
    JPanel panMainPane;
    JPanel panCenterMainPane;
    String labelRouteNo = "Route No: ";
    String labelTripNo = "Trip no: ";
    String labelCurStop = "Current Stop";
    String labelNxtStop = "Next Stop";
    String labelPreStop = "Previous Stop";
    String labelAppStop = "App Stop";

    public panMap(JPanel panMainPan, JPanel panCenterMainPan) {

        initComponents();
        panMainPane = panMainPan;
        panCenterMainPane = panCenterMainPan;
        lblRouteDeviate.setText("");
        scrollPanStops.setVisible(false);
        imgRoadBus2.setVisible(false);
        imgRoadBus3.setVisible(false);
        short no_of_stops_in_a_route = getNoStopsRoute();
        int i = 0;
        try {
            if (getCurStopNo() < no_of_stops_in_a_route) {
                if (getCurStopNo() > 0) {
                    str = "  " + clsSharedVariables.gps_data[getCurStopNo() - 1].stop_name + "\n  ";
                    txtMapCurStop.setText(str);
                    if (getCurStopNo() + 1 < no_of_stops_in_a_route) {
                        str = "  " + clsSharedVariables.gps_data[getCurStopNo()].stop_name + "  "; // + clsSharedVariables.gps_data[getCurStopNo() + 1].reg1_stop_name ;
                        txtMapNxtStop.setText(str);
                        txtMapNxtStop.setVisible(true);
                        imgDots2.setVisible(true);
                    }
                    txtMapCurStop.setVisible(true);
                    imgDots1.setVisible(true);

                } else {
                    str = "  " + clsSharedVariables.gps_data[getCurStopNo()].stop_name + "  ";
                    txtMapCurStop.setText(str);
                    if (getCurStopNo() + 1 < no_of_stops_in_a_route) {
                        str = "  " + clsSharedVariables.gps_data[getCurStopNo() + 1].stop_name + "  "; // + clsSharedVariables.gps_data[getCurStopNo() + 1].reg1_stop_name ;
                        txtMapNxtStop.setText(str);
                        txtMapNxtStop.setVisible(true);
                        imgDots2.setVisible(true);
                    }
                    txtMapCurStop.setVisible(true);
                    imgDots1.setVisible(true);
                }

                str = clsSharedVariables.gps_data[0].stop_name + " - " + clsSharedVariables.gps_data[getNoStopsRoute() - 1].stop_name;
                if (lang_type == LANG_HINDI) {
                    if (clsSharedVariables.gps_data[0].reg1_stop_name != null && clsSharedVariables.gps_data[getNoStopsRoute() - 1].reg1_stop_name != null) {
                        str = clsSharedVariables.gps_data[0].reg1_stop_name + " - " + clsSharedVariables.gps_data[getNoStopsRoute() - 1].reg1_stop_name;

                    }
                } else if (lang_type == LANG_REG) {
                    if (clsSharedVariables.gps_data[0].reg2_stop_name != null && clsSharedVariables.gps_data[getNoStopsRoute() - 1].reg2_stop_name != null) {
                        str = clsSharedVariables.gps_data[0].reg2_stop_name + " - " + clsSharedVariables.gps_data[getNoStopsRoute() - 1].reg2_stop_name;

                    }
                }

                lblSrcDes.setText(str);
            } else {
                txtMapCurStop.setVisible(false);
                txtMapNxtStop.setVisible(false);
                imgDots1.setVisible(false);
                imgDots2.setVisible(false);
                txtCurStop.setText("");
                txtNextStop.setText("");
                txtDistance.setText("");
                txtEta.setText("");
                txtCurSpeed.setText("");
            }

            if (getCurTripNo() >= getCurSchNoTrips() && getSchRouteEnable() == clsDefines.SCHDEULE_ENABLE) {
                txtMapCurStop.setVisible(false);
                txtMapNxtStop.setVisible(false);
                imgDots1.setVisible(false);
                imgDots2.setVisible(false);
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

    @Override
    public void removeNotify() {
        super.removeNotify();
        stopTimer();
    }

    public final void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 1000, 2000);
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

            int i;
            short no_of_stops_in_a_route = getNoStopsRoute();
            byte cur_stop_no = getCurStopNo();

            final JLabel[] tv = new JLabel[no_of_stops_in_a_route];

            panStops.removeAll();

            for (i = 0; i < no_of_stops_in_a_route; i++) {
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
                //  tv[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dots.png"))); // NOI18N

                tv[i].setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                tv[i].setVerticalTextPosition(javax.swing.SwingConstants.TOP);

                if (gps_data[i].stop_identify_status == true) {
                    tv[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/red_dots.png"))); // NOI18N

                } else if (i == cur_stop_no) {
                    tv[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/green_dots.png"))); // NOI18N

                } else {
                    tv[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/yellow_dots.png"))); // NOI18N

                }
                panStops.add(tv[i]);

            }

        } catch (Exception ex) {

        }
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                byte read_audio_state = 0;
                short no_of_stops_in_a_route = getNoStopsRoute();
                try {

                    short cur_trip_no = getCurTripNo();
                    byte cur_stop_no = getCurStopNo();
                    lat = clsSharedVariables.getCurLatitude();
                    longi = getCurLongitude();
                    String str;
                    SwingUtilities.invokeLater(() -> {
                               double distanceKm = getShowStopStopDis() / 1000.0;
                               txtDistance.setText(df.format(distanceKm) + " km");
                               txtCurSpeed.setText(String.valueOf(getGpsSpeed()) + " Kmph");
});


                    setHarshAcc(false);

                    if (!l_schid.equals(get_driver_id())) {
                        l_schid = get_driver_id();
                        SwingUtilities.invokeLater(() -> {
                        });
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

                        read_audio_state = getMapStopState();
                        if (p_map_stop_state != read_audio_state) {
                            if (read_audio_state == clsDefines.NON_AUDIO_STOP_STATE) {
                                identify_bus_locating();
                                load_all_Stops();
                                //current stop details
                                if (cur_stop_no < no_of_stops_in_a_route) {
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name + "   ";
                                    }
                                    final String str1 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapCurStop.setText(str1);
                                        txtMapCurStop.setVisible(true);
                                        imgDots1.setVisible(true);
                                    });
                                } else {
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name + "   ";
                                    }
                                    final String str1 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapCurStop.setText(str1);
                                    });
                                }
                                //next stop details
                                if (cur_stop_no + 1 < no_of_stops_in_a_route) {
                                    txtMapNxtStop.setVisible(true);
                                    imgDots2.setVisible(true);
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no + 1].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no + 1].reg1_stop_name + "   ";
                                    }
                                    final String str1 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapNxtStop.setText(str1);
                                    });
                                } else {

                                    SwingUtilities.invokeLater(() -> {
                                        txtMapNxtStop.setVisible(false);
                                        imgDots2.setVisible(false);
                                    });
                                }
                            } else if (read_audio_state == clsDefines.IDENTIFY_AUDIO_STOP_STATE) {
                                identify_bus_locating();
                                load_all_Stops();
                                //current stop details

                                if (lang_type == LANG_ENGLISH) {
                                    str = "   " + clsSharedVariables.gps_data[cur_stop_no].stop_name + "   ";
                                } else {
                                    str = "   " + clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name + "   ";
                                }
                                final String str1 = str;
                                SwingUtilities.invokeLater(() -> {
                                    txtMapCurStop.setVisible(true);
                                    txtMapCurStop.setText(str1);
                                    imgDots1.setVisible(true);
                                });
                                //next stop details
                                if (cur_stop_no + 1 < no_of_stops_in_a_route) {

                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no + 1].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no + 1].reg1_stop_name + "   ";
                                    }
                                    final String str2 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapNxtStop.setVisible(true);
                                        txtMapNxtStop.setText(str2);
                                        imgDots2.setVisible(true);
                                    });
                                } else {
                                    txtMapNxtStop.setVisible(false);
                                    imgDots2.setVisible(false);
                                }
                            } else if (read_audio_state == clsDefines.CUR_AUDIO_STOP_STATE) {
                                load_all_Stops();
                                cur_stop_bus_locating();

                                SwingUtilities.invokeLater(new Runnable() {

                                    public void run() {
                                        txtMapCurStop.setVisible(true);
                                        imgDots1.setVisible(true);
                                    }
                                });
                                if (cur_stop_no > 0) {
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name + "   ";
                                    }

                                    final String str1 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapCurStop.setText(str1);
                                    });

                                    if (cur_stop_no + 1 < no_of_stops_in_a_route) {

                                        if (lang_type == LANG_ENGLISH) {
                                            str = "   " + clsSharedVariables.gps_data[cur_stop_no + 1].stop_name + "   ";
                                        } else {
                                            str = "   " + clsSharedVariables.gps_data[cur_stop_no + 1].reg1_stop_name + "   ";
                                        }
                                        final String str2 = str;
                                        SwingUtilities.invokeLater(() -> {
                                            txtMapNxtStop.setVisible(true);
                                            imgDots2.setVisible(true);
                                            txtMapNxtStop.setText(str2);
                                        });
                                    } else {
                                        txtMapNxtStop.setVisible(false);
                                        imgDots2.setVisible(false);

                                    }
                                } else if (cur_stop_no == 0) {
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name + "   ";
                                    }
                                    final String str1 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapCurStop.setText(str1);

                                        txtMapNxtStop.setVisible(true);
                                        imgDots2.setVisible(true);
                                    });
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no + 1].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no + 1].reg1_stop_name + "   ";
                                    }
                                    final String str2 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapNxtStop.setText(str2);
                                    });
                                }

                            } else if (read_audio_state == clsDefines.CUR_INC_AUDIO_STOP_STATE) {
                                if (cur_stop_no > 0 && cur_stop_no < no_of_stops_in_a_route) {
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no - 1].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no - 1].reg1_stop_name + "   ";
                                    }
                                    final String str1 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapCurStop.setText(str1);
                                        txtMapCurStop.setVisible(true);
                                        imgDots1.setVisible(true);
                                    });

                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name + "   ";
                                    }
                                    final String str2 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapNxtStop.setText(str2);
                                        txtMapNxtStop.setVisible(true);
                                        imgDots2.setVisible(true);
                                    });
                                }
                                cur_stop_bus_locating();
                            } else if (read_audio_state == clsDefines.NEXT_AUDIO_STOP_STATE) {
                                if (cur_stop_no < no_of_stops_in_a_route) {
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no - 1].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no - 1].reg1_stop_name + "   ";
                                    }

                                    final String str2 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapCurStop.setText(str2);
                                        txtMapCurStop.setVisible(true);
                                        imgDots1.setVisible(true);
                                    });
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name + "   ";
                                    }

                                    final String str3 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapNxtStop.setText(str3);
                                        txtMapNxtStop.setVisible(true);
                                        imgDots2.setVisible(true);
                                    });
                                }
                                next_stop_bus_locating();
                            } else if (read_audio_state == clsDefines.APP_AUDIO_STOP_STATE) {
                                if (cur_stop_no < no_of_stops_in_a_route) {
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no - 1].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no - 1].reg1_stop_name + "   ";
                                    }
                                    final String str1 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapCurStop.setText(str1);
                                        txtMapCurStop.setVisible(true);
                                        imgDots1.setVisible(true);
                                    });
                                    if (lang_type == LANG_ENGLISH) {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].stop_name + "   ";
                                    } else {
                                        str = "   " + clsSharedVariables.gps_data[cur_stop_no].reg1_stop_name + "   ";
                                    }
                                    final String str2 = str;
                                    SwingUtilities.invokeLater(() -> {
                                        txtMapNxtStop.setText(str2);
                                        txtMapNxtStop.setVisible(true);
                                        imgDots2.setVisible(true);
                                    });
                                }
                                if (p_map_stop_state == clsDefines.IDENTIFY_AUDIO_STOP_STATE) {
                                    cur_stop_bus_locating();
                                } else {
                                    app_stop_bus_locating();
                                }
                            } else if (read_audio_state == clsDefines.ENDSTOP_AUDIO_STOP_STATE) {
                                if (lang_type == LANG_ENGLISH) {
                                    str = "   " + clsSharedVariables.gps_data[no_of_stops_in_a_route - 1].stop_name + "   ";
                                } else {
                                    str = "   " + clsSharedVariables.gps_data[no_of_stops_in_a_route - 1].reg1_stop_name + "   ";
                                }
                                final String str1 = str;
                                SwingUtilities.invokeLater(() -> {
                                    txtMapCurStop.setText(str1);
                                    txtMapCurStop.setVisible(true);
                                    imgDots1.setVisible(true);
                                });

                                str = "";
                                final String str2 = str;
                                SwingUtilities.invokeLater(() -> {
                                    txtMapNxtStop.setText(str2);
                                    txtMapNxtStop.setVisible(false);
                                    imgDots2.setVisible(false);
                                });
                                cur_stop_bus_locating();
                                load_all_Stops();
                            }
                            p_map_stop_state = read_audio_state;
                        }
                        state = getStopState();
                        if (l_stop_state != state) {
                            l_stop_state = state;
                            if (state == clsDefines.STOP_STATE_IDENTIFYING) {

                                SwingUtilities.invokeLater(() -> {
                                    txtViewCurrentStop.setText("             "); //getResources().getText(R.string.identifyingStop));
                                    txtCurStop.setText(""); //clsSharedVariables.gps_data[cur_stop_no].stop_name);
                                    txtViewNextStop.setText(labelNxtStop);
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
                            }
                        } else if (state == clsDefines.CUR_STOP_STATE) {
                            SwingUtilities.invokeLater(() -> {
                                txtViewCurrentStop.setText(labelCurStop);
                                txtViewNextStop.setText(labelNxtStop);
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
                                txtViewCurrentStop.setText(labelCurStop);
                                txtViewNextStop.setText(labelNxtStop);
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
                        } else if (state == clsDefines.ROUTE_DEVIATE_STATE) {
                            route_deviate = true;
                            SwingUtilities.invokeLater(() -> {
                                txtViewNextStop.setText("Route Deviated");
                            });
                        } else if (state == clsDefines.APP_STOP_STATE) {
                            if (cur_stop_no > 0) {
                                SwingUtilities.invokeLater(() -> {
                                    txtViewCurrentStop.setText(labelPreStop);
                                    txtViewNextStop.setText(labelAppStop);
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
                                    txtViewCurrentStop.setText(labelPreStop);
                                    txtViewNextStop.setText(labelAppStop);

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
                            SwingUtilities.invokeLater(() -> {
                                lblRouteNo.setText(labelRouteNo + route_no);
                            });
                        }
                        if (l_cur_stop_no != cur_stop_no && cur_stop_no > 0) {
                            //l_cur_stop_no = cur_stop_no;
                            if (route_deviate == false) {
                            }
                            route_deviate = false;
                        }
                        if (cur_trip_no != l_cur_trip_no) {
                            SwingUtilities.invokeLater(() -> {
                                lblTripNo.setText(labelTripNo + String.valueOf(cur_trip_no + 1));
                                if (getNoStopsRoute() > 0) {
                                    String str1 = clsSharedVariables.gps_data[0].stop_name + " - " + clsSharedVariables.gps_data[getNoStopsRoute() - 1].stop_name;
                                    if (lang_type == LANG_HINDI) {
                                        if (clsSharedVariables.gps_data[0].reg1_stop_name != null && clsSharedVariables.gps_data[getNoStopsRoute() - 1].reg1_stop_name != null) {
                                            str1 = clsSharedVariables.gps_data[0].reg1_stop_name + " - " + clsSharedVariables.gps_data[getNoStopsRoute() - 1].reg1_stop_name;

                                        }
                                    } else if (lang_type == LANG_REG) {
                                        if (clsSharedVariables.gps_data[0].reg2_stop_name != null && clsSharedVariables.gps_data[getNoStopsRoute() - 1].reg2_stop_name != null) {
                                            str1 = clsSharedVariables.gps_data[0].reg2_stop_name + " - " + clsSharedVariables.gps_data[getNoStopsRoute() - 1].reg2_stop_name;

                                        }
                                    }
                                    lblSrcDes.setText(str1);
                                } else {
                                    lblSrcDes.setText("");
                                }
                                lblRouteNo.setText(labelRouteNo + getCurSchRouteNo(cur_trip_no));
                                //lblSchId.setText(labelSchId + (get_driver_id()));
                            });
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
                                    imgRoadBus.setVisible(true);
                                } else if (p_map_stop_state == clsDefines.NEXT_AUDIO_STOP_STATE) {
                                    imgRoadBus2.setVisible(true);
                                } else if (p_map_stop_state == clsDefines.APP_AUDIO_STOP_STATE) {
                                    imgRoadBus3.setVisible(true);//noemal
                                }
                            });
                        } else if (prev_speed == 0 && avg_speed != 0) {
                            SwingUtilities.invokeLater(() -> {
                                prev_speed = (short) avg_speed;
                                if (p_map_stop_state == clsDefines.CUR_AUDIO_STOP_STATE || p_map_stop_state == CUR_INC_AUDIO_STOP_STATE || p_map_stop_state == ENDSTOP_AUDIO_STOP_STATE) {
                                    imgRoadBus.setVisible(true); //green
                                } else if (p_map_stop_state == clsDefines.NEXT_AUDIO_STOP_STATE) {
                                    imgRoadBus2.setVisible(true); //green
                                } else if (p_map_stop_state == clsDefines.APP_AUDIO_STOP_STATE) {
                                    imgRoadBus3.setVisible(true);//green
                                }
                            });
                        }
                    }

                } catch (Exception ex) {

                }
            }

        };
    }

    private void next_stop_bus_locating() {
        SwingUtilities.invokeLater(() -> {
            imgRoadBus.setVisible(false);
            imgRoadBus2.setVisible(true);
            imgRoadBus3.setVisible(false);
        });
    }

    private void app_stop_bus_locating() {
        SwingUtilities.invokeLater(() -> {
            imgRoadBus.setVisible(false);
            imgRoadBus2.setVisible(false);
            imgRoadBus3.setVisible(true);
        });

    }

    private void cur_stop_bus_locating() {
        SwingUtilities.invokeLater(() -> {
            imgRoadBus.setVisible(true);
            imgRoadBus2.setVisible(false);
            imgRoadBus3.setVisible(false);
        });
    }

    private void identify_bus_locating() {
        SwingUtilities.invokeLater(() -> {
            imgRoadBus.setVisible(true);
            imgRoadBus2.setVisible(false);
            imgRoadBus3.setVisible(false);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        imgRoadBus2 = new javax.swing.JLabel();
        imgRoadBus3 = new javax.swing.JLabel();
        imgRoadBus = new javax.swing.JLabel();
        scrollPanStops = new javax.swing.JScrollPane();
        panStops = new javax.swing.JPanel();
        txtMapCurStop = new javax.swing.JLabel();
        txtMapNxtStop = new javax.swing.JLabel();
        imgDots1 = new javax.swing.JLabel();
        imgDots2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTripNo = new javax.swing.JLabel();
        lblRouteNo = new javax.swing.JLabel();
        lblRouteDeviate = new javax.swing.JLabel();
        btnRouteBack = new javax.swing.JButton();
        lblSrcDes = new javax.swing.JLabel();
        chkAllStops = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(23, 29, 32));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        setPreferredSize(new java.awt.Dimension(700, 500));

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        txtViewNextStop.setFont(txtViewNextStop.getFont().deriveFont(txtViewNextStop.getFont().getSize()+9f));
        txtViewNextStop.setForeground(new java.awt.Color(255, 255, 255));
        txtViewNextStop.setText("Next Stop");

        txtViewCurrentStop.setFont(txtViewCurrentStop.getFont().deriveFont(txtViewCurrentStop.getFont().getSize()+9f));
        txtViewCurrentStop.setForeground(new java.awt.Color(255, 255, 255));
        txtViewCurrentStop.setText("Current Stop");

        txtViewDistance.setFont(txtViewDistance.getFont().deriveFont(txtViewDistance.getFont().getSize()+9f));
        txtViewDistance.setForeground(new java.awt.Color(255, 255, 255));
        txtViewDistance.setText("Distance");

        txtViewEta.setFont(txtViewEta.getFont().deriveFont(txtViewEta.getFont().getSize()+9f));
        txtViewEta.setForeground(new java.awt.Color(255, 255, 255));
        txtViewEta.setText("ETA");

        txtCurStop.setFont(txtCurStop.getFont().deriveFont(txtCurStop.getFont().getSize()+5f));
        txtCurStop.setForeground(new java.awt.Color(255, 255, 255));
        txtCurStop.setPreferredSize(new java.awt.Dimension(180, 24));

        txtNextStop.setFont(txtNextStop.getFont().deriveFont(txtNextStop.getFont().getSize()+5f));
        txtNextStop.setForeground(new java.awt.Color(255, 255, 255));
        txtNextStop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNextStop.setPreferredSize(new java.awt.Dimension(180, 24));

        txtDistance.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtDistance.setForeground(new java.awt.Color(255, 255, 255));
        txtDistance.setText("0km");
        txtDistance.setPreferredSize(new java.awt.Dimension(77, 24));

        txtEta.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtEta.setForeground(new java.awt.Color(255, 255, 255));
        txtEta.setText("0 mins");
        txtEta.setPreferredSize(new java.awt.Dimension(48, 24));

        txtCurSpeed.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtCurSpeed.setForeground(new java.awt.Color(255, 255, 255));
        txtCurSpeed.setText("0 KMPH");
        txtCurSpeed.setPreferredSize(new java.awt.Dimension(77, 24));

        txtViewSpeed.setFont(txtViewSpeed.getFont().deriveFont(txtViewSpeed.getFont().getSize()+9f));
        txtViewSpeed.setForeground(new java.awt.Color(255, 255, 255));
        txtViewSpeed.setText("Speed");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/roadnew.png"))); // NOI18N
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setIconTextGap(0);
        jLabel1.setMaximumSize(new java.awt.Dimension(500, 128));
        jLabel1.setMinimumSize(new java.awt.Dimension(500, 128));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtCurStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtViewCurrentStop, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtViewNextStop, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNextStop, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtViewDistance)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtViewEta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtViewSpeed)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCurSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtViewEta)
                            .addComponent(txtViewSpeed))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDistance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCurSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtViewNextStop)
                            .addComponent(txtViewCurrentStop)
                            .addComponent(txtViewDistance))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCurStop, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(txtNextStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 290));

        imgRoadBus2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/busicon.png"))); // NOI18N
        imgRoadBus2.setPreferredSize(new java.awt.Dimension(60, 30));

        imgRoadBus3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/busicon.png"))); // NOI18N
        imgRoadBus3.setPreferredSize(new java.awt.Dimension(60, 30));

        imgRoadBus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/busicon.png"))); // NOI18N
        imgRoadBus.setPreferredSize(new java.awt.Dimension(60, 30));

        scrollPanStops.setPreferredSize(new java.awt.Dimension(692, 141));
        scrollPanStops.setRequestFocusEnabled(false);

        panStops.setPreferredSize(new java.awt.Dimension(2000, 120));
        panStops.setLayout(new javax.swing.BoxLayout(panStops, javax.swing.BoxLayout.LINE_AXIS));
        scrollPanStops.setViewportView(panStops);

        txtMapCurStop.setFont(txtMapCurStop.getFont().deriveFont((txtMapCurStop.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD, txtMapCurStop.getFont().getSize()+5));
        txtMapCurStop.setForeground(new java.awt.Color(239, 201, 175));
        txtMapCurStop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMapCurStop.setText("Stop");
        txtMapCurStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txtMapCurStop.setPreferredSize(new java.awt.Dimension(300, 20));
        txtMapCurStop.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        txtMapNxtStop.setFont(txtMapNxtStop.getFont().deriveFont((txtMapNxtStop.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD, txtMapNxtStop.getFont().getSize()+5));
        txtMapNxtStop.setForeground(new java.awt.Color(244, 180, 26));
        txtMapNxtStop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMapNxtStop.setText("STOP1");
        txtMapNxtStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txtMapNxtStop.setPreferredSize(new java.awt.Dimension(300, 20));
        txtMapNxtStop.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        imgDots1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dots.png"))); // NOI18N
        imgDots1.setPreferredSize(new java.awt.Dimension(30, 30));

        imgDots2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dots.png"))); // NOI18N
        imgDots2.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(imgDots1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMapCurStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(imgDots2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtMapNxtStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 79, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(scrollPanStops, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(imgRoadBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(imgRoadBus2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(imgRoadBus3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {imgRoadBus, imgRoadBus2, imgRoadBus3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPanStops, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMapCurStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMapNxtStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgDots1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgDots2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgRoadBus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgRoadBus2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imgRoadBus3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {imgRoadBus, imgRoadBus2});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtMapCurStop, txtMapNxtStop});

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 70));

        lblTripNo.setBackground(new java.awt.Color(0, 0, 102));
        lblTripNo.setFont(lblTripNo.getFont().deriveFont(lblTripNo.getFont().getStyle() & ~java.awt.Font.BOLD, lblTripNo.getFont().getSize()+5));
        lblTripNo.setForeground(new java.awt.Color(255, 255, 255));
        lblTripNo.setText("Trip No");

        lblRouteNo.setBackground(new java.awt.Color(0, 0, 102));
        lblRouteNo.setFont(lblRouteNo.getFont().deriveFont(lblRouteNo.getFont().getStyle() & ~java.awt.Font.BOLD, lblRouteNo.getFont().getSize()+7));
        lblRouteNo.setForeground(new java.awt.Color(255, 255, 255));
        lblRouteNo.setText("Route ID :");

        lblRouteDeviate.setFont(lblRouteDeviate.getFont().deriveFont(lblRouteDeviate.getFont().getStyle() | java.awt.Font.BOLD, lblRouteDeviate.getFont().getSize()+3));
        lblRouteDeviate.setForeground(new java.awt.Color(255, 255, 255));
        lblRouteDeviate.setText("Route Deviated");

        btnRouteBack.setBackground(new java.awt.Color(85, 85, 85));
        btnRouteBack.setFont(btnRouteBack.getFont().deriveFont(btnRouteBack.getFont().getStyle() | java.awt.Font.BOLD, btnRouteBack.getFont().getSize()+7));
        btnRouteBack.setForeground(new java.awt.Color(255, 255, 255));
        btnRouteBack.setText("ROUTE");
        btnRouteBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnRouteBack.setPreferredSize(new java.awt.Dimension(100, 35));
        btnRouteBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRouteBackActionPerformed(evt);
            }
        });

        lblSrcDes.setFont(lblSrcDes.getFont().deriveFont(lblSrcDes.getFont().getStyle() & ~java.awt.Font.BOLD, lblSrcDes.getFont().getSize()+3));
        lblSrcDes.setForeground(new java.awt.Color(255, 255, 255));
        lblSrcDes.setText("SRC - DES");

        chkAllStops.setBackground(new java.awt.Color(98, 143, 188));
        chkAllStops.setFont(chkAllStops.getFont().deriveFont(chkAllStops.getFont().getStyle() | java.awt.Font.BOLD, chkAllStops.getFont().getSize()+7));
        chkAllStops.setForeground(new java.awt.Color(255, 255, 255));
        chkAllStops.setText("All Stops");
        chkAllStops.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAllStopsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblSrcDes, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblTripNo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(lblRouteDeviate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblRouteNo, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRouteBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkAllStops, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(52, 52, 52))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRouteBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAllStops)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblRouteNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTripNo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRouteDeviate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(lblSrcDes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkAllStopsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAllStopsActionPerformed
        // TODO add your handling code here:

        if (chkAllStops.isSelected()) {
            load_all_Stops();

            identify_bus_locating();

            txtMapCurStop.setVisible(false);
            txtMapNxtStop.setVisible(false);
            imgDots1.setVisible(false);
            imgDots2.setVisible(false);
            scrollPanStops.setVisible(true);
            all_stops = true;
        } else {
            p_map_stop_state = clsDefines.NONE_AUDIO_STOP_STATE;
            all_stops = false;
            scrollPanStops.setVisible(false);
            txtMapCurStop.setVisible(true);
            txtMapNxtStop.setVisible(true);
            imgDots1.setVisible(true);
            imgDots2.setVisible(true);
        }
    }//GEN-LAST:event_chkAllStopsActionPerformed

    private void btnRouteBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRouteBackActionPerformed
        // TODO add your handling code here:
        PanelRoute objPanel = new PanelRoute(panMainPane, panCenterMainPane);
        panMainPane.removeAll();
        panMainPane.setLayout(new java.awt.BorderLayout());
        panMainPane.add(objPanel);
        panMainPane.revalidate();
        panMainPane.repaint();
    }//GEN-LAST:event_btnRouteBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRouteBack;
    private javax.swing.JCheckBox chkAllStops;
    private javax.swing.JLabel imgDots1;
    private javax.swing.JLabel imgDots2;
    private javax.swing.JLabel imgRoadBus;
    private javax.swing.JLabel imgRoadBus2;
    private javax.swing.JLabel imgRoadBus3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblRouteDeviate;
    private javax.swing.JLabel lblRouteNo;
    private javax.swing.JLabel lblSrcDes;
    private javax.swing.JLabel lblTripNo;
    private javax.swing.JPanel panStops;
    private javax.swing.JScrollPane scrollPanStops;
    private javax.swing.JLabel txtCurSpeed;
    private javax.swing.JLabel txtCurStop;
    private javax.swing.JLabel txtDistance;
    private javax.swing.JLabel txtEta;
    private javax.swing.JLabel txtMapCurStop;
    private javax.swing.JLabel txtMapNxtStop;
    private javax.swing.JLabel txtNextStop;
    private javax.swing.JLabel txtViewCurrentStop;
    private javax.swing.JLabel txtViewDistance;
    private javax.swing.JLabel txtViewEta;
    private javax.swing.JLabel txtViewNextStop;
    private javax.swing.JLabel txtViewSpeed;
    // End of variables declaration//GEN-END:variables
}
