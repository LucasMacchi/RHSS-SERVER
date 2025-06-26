package rhss_server.rhss_server.Utils;

import java.util.List;

import rhss_server.rhss_server.Tables.AltaTable;
import rhss_server.rhss_server.Tables.ArchivoModel;
import rhss_server.rhss_server.Tables.AusenteModel;
import rhss_server.rhss_server.Tables.LegajosTable;
import rhss_server.rhss_server.Tables.LicenciaTable;
import rhss_server.rhss_server.Tables.NovedadesModel;
import rhss_server.rhss_server.Tables.PersonalTable;
import rhss_server.rhss_server.Tables.SancionModel;

public class NovLegajo {
    public LegajosTable legajo;
    public NovedadesModel novedad;
    public List<NovedadesModel> leg_novedades;
    public List<AusenteModel> ausentes;
    public List<SancionModel> sanciones;
    public List<PersonalTable> personal;
    public List<LicenciaTable> licencias;
    public List<ArchivoModel> archivos;
    public List<AltaTable> altas;

    public NovLegajo(LegajosTable leg, NovedadesModel nov, List<AusenteModel> aus,
    List<SancionModel> sanciones,List<PersonalTable> personalNov, 
    List<LicenciaTable> licencias,List<ArchivoModel> archivos, List<AltaTable> altas){
        this.legajo = leg;
        this.novedad = nov;
        this.ausentes = aus;
        this.sanciones = sanciones;
        this.personal = personalNov;
        this.licencias = licencias;
        this.archivos = archivos;
        this.altas = altas;
    }
}
