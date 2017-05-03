package com.zsy.timeassistant.bean;

import java.util.List;

/*
 * 项目名:    Calendar
 * 创建时间:  2017/4/22 on 12:36
 * 描述:     TODO
 */
public class WeatherBean {

    private List<HeWeather5Bean> HeWeather5;

    public List<HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeWeather5Bean> HeWeather5) {
        this.HeWeather5 = HeWeather5;
    }

    public static class HeWeather5Bean {
        /**
         * basic : {"city":"南京","cnty":"中国","id":"CN101190101","lat":"32.041544","lon":"118.767413","update":{"loc":"2017-04-22 12:51","utc":"2017-04-22 04:51"}}
         * daily_forecast : [{"astro":{"mr":"02:40","ms":"14:08","sr":"05:29","ss":"18:39"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-04-22","hum":"56","pcpn":"1.8","pop":"71","pres":"1015","tmp":{"max":"22","min":"11"},"uv":"8","vis":"20","wind":{"deg":"153","dir":"北风","sc":"微风","spd":"6"}},{"astro":{"mr":"03:20","ms":"15:10","sr":"05:28","ss":"18:39"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2017-04-23","hum":"55","pcpn":"0.0","pop":"0","pres":"1015","tmp":{"max":"24","min":"12"},"uv":"8","vis":"20","wind":{"deg":"153","dir":"南风","sc":"微风","spd":"6"}},{"astro":{"mr":"03:59","ms":"16:13","sr":"05:27","ss":"18:40"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2017-04-24","hum":"56","pcpn":"0.0","pop":"0","pres":"1014","tmp":{"max":"27","min":"16"},"uv":"9","vis":"20","wind":{"deg":"146","dir":"东南风","sc":"微风","spd":"6"}},{"astro":{"mr":"04:38","ms":"17:19","sr":"05:26","ss":"18:41"},"cond":{"code_d":"300","code_n":"300","txt_d":"阵雨","txt_n":"阵雨"},"date":"2017-04-25","hum":"69","pcpn":"2.0","pop":"100","pres":"1013","tmp":{"max":"23","min":"15"},"uv":"6","vis":"17","wind":{"deg":"76","dir":"东北风","sc":"微风","spd":"10"}},{"astro":{"mr":"05:20","ms":"18:26","sr":"05:25","ss":"18:42"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-04-26","hum":"59","pcpn":"0.3","pop":"55","pres":"1018","tmp":{"max":"21","min":"12"},"uv":"5","vis":"17","wind":{"deg":"51","dir":"东北风","sc":"微风","spd":"10"}},{"astro":{"mr":"06:03","ms":"19:36","sr":"05:24","ss":"18:42"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-04-27","hum":"54","pcpn":"0.0","pop":"4","pres":"1016","tmp":{"max":"22","min":"14"},"uv":"5","vis":"19","wind":{"deg":"130","dir":"西南风","sc":"微风","spd":"6"}},{"astro":{"mr":"06:51","ms":"20:44","sr":"05:23","ss":"18:43"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2017-04-28","hum":"53","pcpn":"0.4","pop":"25","pres":"1014","tmp":{"max":"26","min":"14"},"uv":"5","vis":"19","wind":{"deg":"207","dir":"西南风","sc":"微风","spd":"5"}}]
         * status : ok
         */

        private BasicBean basic;
        private String status;
        private List<DailyForecastBean> daily_forecast;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class BasicBean {
            /**
             * city : 南京
             * cnty : 中国
             * id : CN101190101
             * lat : 32.041544
             * lon : 118.767413
             * update : {"loc":"2017-04-22 12:51","utc":"2017-04-22 04:51"}
             */

            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                /**
                 * loc : 2017-04-22 12:51
                 * utc : 2017-04-22 04:51
                 */

                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * astro : {"mr":"02:40","ms":"14:08","sr":"05:29","ss":"18:39"}
             * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
             * date : 2017-04-22
             * hum : 56
             * pcpn : 1.8
             * pop : 71
             * pres : 1015
             * tmp : {"max":"22","min":"11"}
             * uv : 8
             * vis : 20
             * wind : {"deg":"153","dir":"北风","sc":"微风","spd":"6"}
             */

            private AstroBean astro;
            private CondBean cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            private TmpBean tmp;
            private String uv;
            private String vis;
            private WindBean wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                /**
                 * mr : 02:40
                 * ms : 14:08
                 * sr : 05:29
                 * ss : 18:39
                 */

                private String mr;
                private String ms;
                private String sr;
                private String ss;

                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBean {
                /**
                 * code_d : 100
                 * code_n : 100
                 * txt_d : 晴
                 * txt_n : 晴
                 */

                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                /**
                 * max : 22
                 * min : 11
                 */

                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBean {
                /**
                 * deg : 153
                 * dir : 北风
                 * sc : 微风
                 * spd : 6
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
