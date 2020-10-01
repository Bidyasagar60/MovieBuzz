package com.knight.moviebuzz.Model.Pojo;

import java.util.List;

public class MovieLanguages {




    private int id;
    private List<TranslationsBean> translations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TranslationsBean> getTranslations() {
        return translations;
    }

    public void setTranslations(List<TranslationsBean> translations) {
        this.translations = translations;
    }

    public static class TranslationsBean {


        private String iso_3166_1;
        private String iso_639_1;
        private String name;
        private String english_name;
        private DataBean data;

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnglish_name() {
            return english_name;
        }

        public void setEnglish_name(String english_name) {
            this.english_name = english_name;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * homepage :
             * overview :
             * runtime : 0
             * tagline :
             * title :
             */

            private String homepage;
            private String overview;
            private int runtime;
            private String tagline;
            private String title;

            public String getHomepage() {
                return homepage;
            }

            public void setHomepage(String homepage) {
                this.homepage = homepage;
            }

            public String getOverview() {
                return overview;
            }

            public void setOverview(String overview) {
                this.overview = overview;
            }

            public int getRuntime() {
                return runtime;
            }

            public void setRuntime(int runtime) {
                this.runtime = runtime;
            }

            public String getTagline() {
                return tagline;
            }

            public void setTagline(String tagline) {
                this.tagline = tagline;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
