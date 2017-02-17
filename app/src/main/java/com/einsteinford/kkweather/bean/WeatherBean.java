package com.einsteinford.kkweather.bean;


import java.util.List;

/**
 * Created by KK on 2017/2/7.
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
         * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2017-02-07 14:51","utc":"2017-02-07 06:51"}}
         * daily_forecast : [{"astro":{"mr":"13:57","ms":"03:41","sr":"07:16","ss":"17:40"},"cond":{"code_d":"400","code_n":"104","txt_d":"小雪","txt_n":"阴"},"date":"2017-02-07","hum":"34","pcpn":"0.0","pop":"27","pres":"1031","tmp":{"max":"2","min":"-4"},"uv":"2","vis":"10","wind":{"deg":"174","dir":"南风","sc":"微风","spd":"3"}},{"astro":{"mr":"14:55","ms":"04:42","sr":"07:15","ss":"17:41"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-02-08","hum":"34","pcpn":"0.0","pop":"0","pres":"1032","tmp":{"max":"4","min":"-7"},"uv":"2","vis":"10","wind":{"deg":"335","dir":"北风","sc":"4-5","spd":"21"}},{"astro":{"mr":"15:58","ms":"05:38","sr":"07:14","ss":"17:43"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-02-09","hum":"24","pcpn":"0.0","pop":"0","pres":"1031","tmp":{"max":"3","min":"-5"},"uv":"2","vis":"10","wind":{"deg":"323","dir":"北风","sc":"4-5","spd":"22"}},{"astro":{"mr":"17:03","ms":"06:26","sr":"07:12","ss":"17:44"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-02-10","hum":"21","pcpn":"0.0","pop":"0","pres":"1031","tmp":{"max":"4","min":"-5"},"uv":"2","vis":"10","wind":{"deg":"323","dir":"北风","sc":"4-5","spd":"20"}},{"astro":{"mr":"18:08","ms":"07:10","sr":"07:11","ss":"17:45"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-02-11","hum":"26","pcpn":"0.0","pop":"0","pres":"1031","tmp":{"max":"7","min":"-4"},"uv":"2","vis":"10","wind":{"deg":"322","dir":"北风","sc":"微风","spd":"0"}},{"astro":{"mr":"19:13","ms":"07:48","sr":"07:10","ss":"17:46"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-02-12","hum":"32","pcpn":"0.0","pop":"0","pres":"1031","tmp":{"max":"8","min":"-4"},"uv":"-999","vis":"10","wind":{"deg":"118","dir":"南风","sc":"微风","spd":"2"}},{"astro":{"mr":"20:15","ms":"08:22","sr":"07:09","ss":"17:47"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-02-13","hum":"35","pcpn":"0.0","pop":"0","pres":"1033","tmp":{"max":"9","min":"-4"},"uv":"-999","vis":"10","wind":{"deg":"148","dir":"南风","sc":"微风","spd":"4"}}]
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
             * city : 北京
             * cnty : 中国
             * id : CN101010100
             * lat : 39.904000
             * lon : 116.391000
             * update : {"loc":"2017-02-07 14:51","utc":"2017-02-07 06:51"}
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
                 * loc : 2017-02-07 14:51
                 * utc : 2017-02-07 06:51
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
             * astro : {"mr":"13:57","ms":"03:41","sr":"07:16","ss":"17:40"}
             * cond : {"code_d":"400","code_n":"104","txt_d":"小雪","txt_n":"阴"}
             * date : 2017-02-07
             * hum : 34
             * pcpn : 0.0
             * pop : 27
             * pres : 1031
             * tmp : {"max":"2","min":"-4"}
             * uv : 2
             * vis : 10
             * wind : {"deg":"174","dir":"南风","sc":"微风","spd":"3"}
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
                 * mr : 13:57
                 * ms : 03:41
                 * sr : 07:16
                 * ss : 17:40
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
                 * code_d : 400
                 * code_n : 104
                 * txt_d : 小雪
                 * txt_n : 阴
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
                 * max : 2
                 * min : -4
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
                 * deg : 174
                 * dir : 南风
                 * sc : 微风
                 * spd : 3
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
