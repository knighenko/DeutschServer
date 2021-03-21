package entity;


public class Task {


    private int id;
    private String rus;
    private String deu;
    private boolean checks;

    public boolean isChecks() {
        return checks;
    }

    public void setChecks(boolean checks) {
        this.checks = checks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        StringBuilder stringBuilder = new StringBuilder("{");

        stringBuilder.append("\"id\":\"").append(id).append('\"').append(", \"rus\":\"").append(rus).append('\"').append(", \"deu\":\"").append(deu).append('\"').append(", \"checks\":\"").append(checks).append('\"').append("}");

        return stringBuilder.toString();
    }

}

