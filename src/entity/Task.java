package entity;


    public class Task {

        private String rus;
        private String deu;
        public String getRus() {
            return rus;
        }

        public void setRus(String rus) {
            this.rus = rus;
        }

        public String getDeu() {
            return deu;
        }

        public void setDeu(String deu) {
            this.deu = deu;
        }
        @Override
        public String toString() {
            StringBuilder stringBuilder=new StringBuilder("{");

            stringBuilder.append("\"rus\":\"").append(rus).append('\"').append(", \"deu\":\"").append(deu).append('\"').append("}");

            return stringBuilder.toString();
        }

    }

