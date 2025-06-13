package rhss_server.rhss_server.Utils;

import java.time.LocalDate;

public class dateShorter {

    private LocalDate date;

    public dateShorter(LocalDate date) {
        this.date = date;
    }

    public String getMonth () {
        int year = date.getYear() - 2000;
        switch (this.date.getMonthValue()) {
            case 1:
                return "EN"+year;
            case 2:
                return "FE"+year;
            case 3:
                return "MA"+year;
            case 4:
                return "AB"+year;
            case 5:
                return "MY"+year;
            case 6:
                return "JN"+year;
            case 7:
                return "JL"+year;
            case 8:
                return "AG"+year;
            case 9:
                return "SE"+year;
            case 10:
                return "OC"+year;
            case 11:
                return "NV"+year;
            case 12:
                return "DI"+year;
            default:
                return "NaN00";
        }
    }
}
