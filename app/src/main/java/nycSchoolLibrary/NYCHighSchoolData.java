package nycSchoolLibrary;

public class NYCHighSchoolData {

    // this is for this https://data.cityofnewyork.us/resource/f9bf-2cp4.json
    private String dbn;
    private String school_name;
    private  String num_of_sat_test_takers ;
    private  String sat_critical_reading_avg_score ;
    private  String sat_math_avg_score ;
    private  String sat_writing_avg_score;

    public String getDbn() {
        return dbn;
    }

    public String getSchool_name() {
        return school_name;
    }

    public String getNum_of_sat_test_takers() {
        return num_of_sat_test_takers;
    }

    public String getSat_critical_reading_avg_score() {
        return sat_critical_reading_avg_score;
    }

    public String getSat_math_avg_score() {
        return sat_math_avg_score;
    }

    public String getSat_writing_avg_score() {
        return sat_writing_avg_score;
    }
}
