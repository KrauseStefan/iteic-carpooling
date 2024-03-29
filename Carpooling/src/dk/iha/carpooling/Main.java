package dk.iha.carpooling;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Main extends Activity {
	
	private int mHour;
    private int mMinute;
    
    private int mDay;
    private int mMonth;
    private int mYear;

    static final int TIME_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID = 1;
	
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_view);
		
		AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_from);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, BYER);
	    textView.setAdapter(adapter);
	    
	    AutoCompleteTextView textView2 = (AutoCompleteTextView) findViewById(R.id.autocomplete_to);
	    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, BYER);
	    textView2.setAdapter(adapter2);
	    
	    EditText time = (EditText) findViewById(R.id.insert_time);
	    
	    
	    
	    final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        updateDisplay();
	    
	}
	
	public void navigateTo(View view)
	{
		Intent intent = new Intent();
		
		switch (view.getId())
		{
		case R.id.createRouteBtn:
			AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_from);
			AutoCompleteTextView textView2 = (AutoCompleteTextView) findViewById(R.id.autocomplete_to);
			
			
	        intent.setClass(this, CreateRoute.class);
	        intent.putExtra("points", new String[]{textView.getText().toString(),textView2.getText().toString()});
	        startActivityForResult(intent, view.getId());
			
			break;
			
		case R.id.searchRouteBtn:
	        intent.setClass(this, SearchForRoute.class);
	        
	        startActivityForResult(intent, view.getId());
			
			break;
			
		case R.id.myRoutesBtn:
	        intent.setClass(this, MyRoutes.class);
	        
	        startActivityForResult(intent, view.getId());
			
			break;
		case R.id.insert_time:
			showDialog(DATE_DIALOG_ID);
			break;

		default:
			Toast.makeText(getApplicationContext(), "Method not implemented jet :(", Toast.LENGTH_SHORT).show();
			
			break;
		}		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.my_profile:
	        	Intent intent = new Intent();
	            intent.setClass(this,UserProfile.class);	            
	            startActivity(intent);
	        	
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case TIME_DIALOG_ID:
	        return new TimePickerDialog(this,
	                mTimeSetListener, mHour, mMinute, true);
	    case DATE_DIALOG_ID:
	    	return new DatePickerDialog(this,
                    mDateSetListener,
                    mYear, mMonth, mDay);
	    }
	    return null;
	}
	
	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
		    new TimePickerDialog.OnTimeSetListener() {
		        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		            mHour = hourOfDay;
		            mMinute = minute;
		            updateDisplay();
		        }
		       };
		       
   private DatePickerDialog.OnDateSetListener mDateSetListener =
           new DatePickerDialog.OnDateSetListener() {

               public void onDateSet(DatePicker view, int year, 
                                     int monthOfYear, int dayOfMonth) {
				mYear = year;
				mMonth = monthOfYear;
				mDay = dayOfMonth;
				showDialog(TIME_DIALOG_ID);
				
			}
	       };
		    
    private void updateDisplay() {
    	
    	EditText time = (EditText) findViewById(R.id.insert_time);
    	time.setText(
            new StringBuilder()
			        .append(mDay).append(".")
			        .append(mMonth + 1).append(".")
			        .append(mYear).append(" ")
                    .append(pad(mHour)).append(":")
                    .append(pad(mMinute))
                    );
    }
    
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


	
	static final String[] BYER = new String[] {"Erslev,7950", "Karby,7960", "Redsted M,7970", "Vils,7980", "Øster Assels,7990", "Thisted,7700", "Hanstholm,7730", "Frøstrup,7741", "Vesløs,7742", "Snedsted,7752", "Bedsted Thy,7755", "Hurup Thy,7760", "Vestervig,7770", "Hjallerup,9320", "Dronninglund,9330", "Asaa,9340", "Dybvad,9352", "Hals,9370", "Tylstrup,9382", "Aabybro,9440", "Løkken,9480", "Brønderslev,9700", "Jerslev J,9740", "Østervrå,9750", "Vrå,9760", "Sæby,9300", "Tårs,9830", "Sindal,9870", "Bindslev,9881", "Frederikshavn,9900", "Strandby,9970", "Jerup,9981", "Ålbæk,9982", "Skagen,9990", "Nibe,9240", "Suldrup,9541", "Aars,9600", "Nørager,9610", "Aalestrup,9620", "Gedsted,9631", "Farsø,9640", "Løgstør,9670", "Ranum,9681", "Læsø,9940", "Svenstrup J,9230", "Gistrup,9260", "Kongerslev,9293", "Hobro,9500", "Arden,9510", "Skørping,9520", "Støvring,9530", "Hadsund,9560", "Bælum,9574", "Terndrup,9575", "Havndal,8970", "Fårup,8990", "Mariager,9550", "Møldrup,9632", "Sulsted,9381", "Vadum,9430", "Brovst,9460", "Pandrup,9490", "Blokhus,9492", "Saltum,9493", "Fjerritslev,9690", "Aalborg,9000", "Aalborg SV,9200", "Aalborg SØ,9210", "Aalborg Øst,9220", "Klarup,9270", "Storvorde,9280", "Vodskov,9310", "Gandrup,9362", "Vestbjerg,9380", "Nørresundby,9400", "Hjørring,9800", "Hirtshals,9850", "Bryrup,8654", "Skanderborg,8660", "Ry,8680", "Horsens,8700", "Hovedgård,8732", "Brædstrup,8740", "Gedved,8751", "Østbirk,8752", "Flemming,8762", "Klovborg,8765", "Stenderup,8781", "Hornsyld,8783", "Kibæk,6933", "Ørnhøj,6973", "Ulfborg,6990", "Sønder Omme,7260", "Stakroge,7270", "Sønder Felding,7280", "Brande,7330", "Herning,7400", "Sunds,7451", "Karup J,7470", "Vildbjerg,7480", "Aulum,7490", "Holstebro,7500", "Haderup,7540", "Sørvad,7550", "Vinderup,7830", "Hjerm,7560", "Vemb,7570", "Struer,7600", "Skive,7800", "Lemvig,7620", "Bøvlingbjerg,7650", "Bækmarksbro,7660", "Harboøre,7673", "Thyborøn,7680", "Thyholm,7790", "Ebeltoft,8400", "Rønde,8410", "Knebel,8420", "Balle,8444", "Hornslet,8543", "Mørke,8544", "Ryomgård,8550", "Kolind,8560", "Trustrup,8570", "Nimtofte,8581", "Auning,8963", "Grenaa,8500", "Glesborg,8585", "Ørum Djurs,8586", "Anholt,8592", "Ørsted,8950", "Allingåbro,8961", "Hadsten,8370", "Trige,8380", "Hinnerup,8382", "Hammel,8450", "Sabro,8471", "Sporup,8472", "Sorring,8641", "Bjerringbro,8850", "Ulstrup,8860", "Langå,8870", "Thorsø,8881", "Fårvang,8882", "Randers SV,8940", "Randers SØ,8960", "Odder,8300", "Malling,8340", "Hundslund,8350", "Randers C,8900", "Randers NV,8920", "Randers NØ,8930", "Spentrup,8981", "Gjerlev J,8983", "Hampen,7362", "Bording,7441", "Engesvang,7442", "Silkeborg,8600", "Kjellerup,8620", "Lemming,8632", "Ans By,8643", "Them,8653", "Viborg,8800", "Rødkærsbro,8840", "Gjern,8883", "Samsø,8305", "Hørning,8362", "Galten,8464", "Låsby,8670", "Århus C,8000", "Århus N,8200", "Århus V,8210", "Brabrand,8220", "Åbyhøj,8230", "Risskov,8240", "Risskov Ø,8245", "Egå,8250", "Viby J,8260", "Højbjerg,8270", "Tranbjerg J,8310", "Mårslet,8320", "Beder,8330", "Solbjerg,8355", "Hasselager,8361", "Tilst,8381", "Harlev J,8462", "Lystrup,8520", "Hjortshøj,8530", "Skødstrup,8541", "Tørring,7160", "Ejstrupholm,7361", "Ikast,7430", "Nørre Snede,8766", "Nørre Nebel,6830", "Ølgod,6870", "Tarm,6880", "Hemmet,6893", "Skjern,6900", "Videbæk,6920", "Lem St,6940", "Ringkøbing,6950", "Hvide Sande,6960", "Spjald,6971", "Tim,6980", "Vejle,7100", "Vejle Øst,7120", "Juelsminde,7130", "Stouby,7140", "Barrit,7150", "Uldum,7171", "Vonge,7173", "Give,7323", "Daugård,8721", "Hedensted,8722", "Løsning,8723", "Rask Mølle,8763", "Højslev,7840", "Spøttrup,7860", "Roslev,7870", "Fur,7884", "Stoholm Jyll,7850", "Tjele,8830", "Løgstrup,8831", "Skals,8832", "Harndrup,5463", "Brenderup Fyn,5464", "Asperup,5466", "Middelfart,5500", "Aarup,5560", "Nørre Aaby,5580", "Gelsted,5591", "Ejby,5592", "Assens,5610", "Vissenbjerg,5492", "Glamsbjerg,5620", "Ebberup,5631", "Millinge,5642", "Haarby,5683", "Tommerup,5690", "Langeskov,5550", "Faaborg,5600", "Broby,5672", "Ringe,5750", "Vester Skerninge,5762", "Stenstrup,5771", "Kværndrup,5772", "Årslev,5792", "Ørbæk,5853", "Gislev,5854", "Ryslinge,5856", "Ferritslev Fyn,5863", "Marslev,5290", "Kerteminde,5300", "Munkebo,5330", "Rynkeby,5350", "Mesinge,5370", "Dalby,5380", "Martofte,5390", "Ullerslev,5540", "Nyborg,5800", "Frørup,5871", "Hesselager,5874", "Odense C,5000", "Odense V,5200", "Odense NV,5210", "Odense SØ,5220", "Odense M,5230", "Odense NØ,5240", "Odense SV,5250", "Odense S,5260", "Odense N,5270", "Agedrup,5320", "Blommenslyst,5491", "Svendborg,5700", "Skårup Fyn,5881", "Vejstrup,5882", "Oure,5883", "Gudme,5884", "Gudbjerg Sydfyn,5892", "Bogense,5400", "Otterup,5450", "Morud,5462", "Søndersø,5471", "Veflinge,5474", "Skamby,5485", "Rudkøbing,5900", "Humble,5932", "Bagenkop,5935", "Tranekær,5953", "Marstal,5960", "Ærøskøbing,5970", "Søby Ærø,5985", "Christiansfeld,6070", "Hejls,6094", "Haderslev,6100", "Vojens,6500", "Gram,6510", "Toftlund,6520", "Agerskov,6534", "Bevtoft,6541", "Sommersted,6560", "Rødding,6630", "Vorbasse,6623", "Hovborg,6682", "Ansager,6823", "Billund,7190", "Grindsted,7200", "Hejnsvig,7250", "Gråsten,6300", "Broager,6310", "Egernsund,6320", "Sønderborg,6400", "Nordborg,6430", "Augustenborg,6440", "Sydals,6470", "Løgumkloster,6240", "Bredebro,6261", "Tønder,6270", "Højer,6280", "Branderup J,6535", "Skærbæk,6780", "Rømø,6792", "Holsted,6670", "Føvling,6683", "Gørding,6690", "Esbjerg,6700", "Esbjerg Ø,6705", "Esbjerg V,6710", "Esbjerg N,6715", "Tjæreborg,6731", "Bramming,6740", "Ribe,6760", "Gredstedbro,6771", "Årre,6818", "Fanø,6720", "Agerbæk,6753", "Varde,6800", "Oksbøl,6840", "Janderup Vestj,6851", "Billum,6852", "Vejers Strand,6853", "Henne,6854", "Outrup,6855", "Blåvand,6857", "Tistrup,6862", "Vejen,6600", "Gesten,6621", "Bække,6622", "Brørup,6650", "Lintrup,6660", "Glejbjerg,6752", "Aabenraa,6200", "Rødekro,6230", "Padborg,6330", "Kruså,6340", "Tinglev,6360", "Bylderup-Bov,6372", "Bolderslev,6392", "Fredericia,7000", "Fredericia,7007", "Kolding,6000", "Egtved,6040", "Almind,6051", "Viuf,6052", "Jordrup,6064", "Bjert,6091", "Sønder Stenderup,6092", "Sjølund,6093", "Vamdrup,6580", "Lunderskov,6640", "Børkop,7080", "Bredsten,7182", "Randbøl,7183", "Vandel,7184", "Jelling,7300", "Gadbjerg,7321", "København C,900", "København C,999", "København K,1000", "København K,1050", "København K,1051", "København K,1052", "København K,1053", "København K,1054", "København K,1055", "København K,1056", "København K,1057", "København K,1058", "København K,1059", "København K,1060", "København K,1061", "København K,1062", "København K,1063", "København K,1064", "København K,1065", "København K,1066", "København K,1067", "København K,1068", "København K,1069", "København K,1070", "København K,1071", "København K,1072", "København K,1073", "København K,1074", "København K,1092", "København K,1093", "København K,1095", "København K,1098", "København K,1100", "København K,1101", "København K,1102", "København K,1103", "København K,1104", "København K,1105", "København K,1106", "København K,1107", "København K,1110", "København K,1111", "København K,1112", "København K,1113", "København K,1114", "København K,1115", "København K,1116", "København K,1117", "København K,1118", "København K,1119", "København K,1120", "København K,1122", "København K,1123", "København K,1124", "København K,1125", "København K,1126", "København K,1127", "København K,1128", "København K,1129", "København K,1130", "København K,1131", "København K,1140", "København K,1147", "København K,1148", "København K,1150", "København K,1151", "København K,1152", "København K,1153", "København K,1154", "København K,1155", "København K,1156", "København K,1157", "København K,1158", "København K,1159", "København K,1160", "København K,1161", "København K,1162", "København K,1164", "København K,1165", "København K,1166", "København K,1167", "København K,1168", "København K,1169", "København K,1170", "København K,1171", "København K,1172", "København K,1173", "København K,1174", "København K,1175", "København K,1200", "København K,1201", "København K,1202", "København K,1203", "København K,1204", "København K,1205", "København K,1206", "København K,1207", "København K,1208", "København K,1209", "København K,1210", "København K,1211", "København K,1213", "København K,1214", "København K,1215", "København K,1216", "København K,1217", "København K,1218", "København K,1219", "København K,1220", "København K,1221", "København K,1240", "København K,1250", "København K,1251", "København K,1253", "København K,1254", "København K,1255", "København K,1256", "København K,1257", "København K,1259", "København K,1260", "København K,1261", "København K,1263", "København K,1264", "København K,1265", "København K,1266", "København K,1267", "København K,1268", "København K,1270", "København K,1271", "København K,1291", "København K,1300", "København K,1301", "København K,1302", "København K,1303", "København K,1304", "København K,1306", "København K,1307", "København K,1308", "København K,1309", "København K,1310", "København K,1311", "København K,1312", "København K,1313", "København K,1314", "København K,1315", "København K,1316", "København K,1317", "København K,1318", "København K,1319", "København K,1320", "København K,1321", "København K,1322", "København K,1323", "København K,1324", "København K,1325", "København K,1326", "København K,1327", "København K,1328", "København K,1329", "København K,1349", "København K,1350", "København K,1352", "København K,1353", "København K,1354", "København K,1355", "København K,1356", "København K,1357", "København K,1358", "København K,1359", "København K,1360", "København K,1361", "København K,1362", "København K,1363", "København K,1364", "København K,1365", "København K,1366", "København K,1367", "København K,1368", "København K,1369", "København K,1370", "København K,1371", "København K,1400", "København K,1401", "København K,1402", "København K,1403", "København K,1406", "København K,1407", "København K,1408", "København K,1409", "København K,1410", "København K,1411", "København K,1412", "København K,1413", "København K,1414", "København K,1415", "København K,1416", "København K,1417", "København K,1418", "København K,1419", "København K,1420", "København K,1421", "København K,1422", "København K,1423", "København K,1424", "København K,1425", "København K,1426", "København K,1427", "København K,1428", "København K,1429", "København K,1430", "København K,1431", "København K,1432", "København K,1433", "København K,1434", "København K,1435", "København K,1436", "København K,1437", "København K,1438", "København K,1439", "København K,1440", "København K,1441", "København K,1448", "København K,1450", "København K,1451", "København K,1452", "København K,1453", "København K,1454", "København K,1455", "København K,1456", "København K,1457", "København K,1458", "København K,1459", "København K,1460", "København K,1462", "København K,1463", "København K,1464", "København K,1466", "København K,1467", "København K,1468", "København K,1470", "København K,1471", "København K,1472", "København V,1500", "København V,1550", "København V,1551", "København V,1552", "København V,1553", "København V,1554", "København V,1555", "København V,1556", "København V,1557", "København V,1558", "København V,1559", "København V,1560", "København V,1561", "København V,1562", "København V,1563", "København V,1564", "København V,1566", "København V,1567", "København V,1568", "København V,1569", "København V,1570", "København V,1571", "København V,1572", "København V,1573", "København V,1574", "København V,1575", "København V,1576", "København V,1577", "København V,1592", "København V,1599", "København V,1600", "København V,1601", "København V,1602", "København V,1603", "København V,1604", "København V,1606", "København V,1607", "København V,1608", "København V,1609", "København V,1610", "København V,1611", "København V,1612", "København V,1613", "København V,1614", "København V,1615", "København V,1616", "København V,1617", "København V,1618", "København V,1619", "København V,1620", "København V,1621", "København V,1622", "København V,1623", "København V,1624", "København V,1630", "København V,1631", "København V,1632", "København V,1633", "København V,1634", "København V,1635", "København V,1640", "København V,1650", "København V,1651", "København V,1652", "København V,1653", "København V,1654", "København V,1655", "København V,1656", "København V,1657", "København V,1658", "København V,1659", "København V,1660", "København V,1661", "København V,1662", "København V,1663", "København V,1664", "København V,1665", "København V,1666", "København V,1667", "København V,1668", "København V,1669", "København V,1670", "København V,1671", "København V,1672", "København V,1673", "København V,1674", "København V,1675", "København V,1676", "København V,1677", "København V,1699", "København V,1700", "København V,1701", "København V,1702", "København V,1703", "København V,1704", "København V,1705", "København V,1706", "København V,1707", "København V,1708", "København V,1709", "København V,1710", "København V,1711", "København V,1712", "København V,1714", "København V,1715", "København V,1716", "København V,1717", "København V,1718", "København V,1719", "København V,1720", "København V,1721", "København V,1722", "København V,1723", "København V,1724", "København V,1725", "København V,1726", "København V,1727", "København V,1728", "København V,1729", "København V,1730", "København V,1731", "København V,1732", "København V,1733", "København V,1734", "København V,1735", "København V,1736", "København V,1737", "København V,1738", "København V,1739", "København V,1748", "København V,1749", "København V,1750", "København V,1751", "København V,1752", "København V,1753", "København V,1754", "København V,1755", "København V,1756", "København V,1757", "København V,1758", "København V,1759", "København V,1760", "København V,1761", "København V,1762", "København V,1763", "København V,1764", "København V,1765", "København V,1766", "København V,1770", "København V,1771", "København V,1772", "København V,1773", "København V,1774", "København V,1775", "København V,1777", "København V,1778", "København V,1780", "København V,1785", "København V,1786", "København V,1787", "København V,1789", "København V,1790", "København V,1795", "København V,1799", "Frederiksberg C,1810", "Frederiksberg,2000", "København Ø,2100", "København N,2200", "København S,2300", "København NV,2400", "København SV,2450", "Valby,2500", "Rødovre,2610", "Brønshøj,2700", "Vanløse,2720", "Kastrup,2770", "Søborg,2860", "Hellerup,2900", "Frederiksberg C,1800", "Frederiksberg C,1801", "Frederiksberg C,1802", "Frederiksberg C,1803", "Frederiksberg C,1804", "Frederiksberg C,1805", "Frederiksberg C,1806", "Frederiksberg C,1807", "Frederiksberg C,1808", "Frederiksberg C,1809", "Frederiksberg C,1811", "Frederiksberg C,1812", "Frederiksberg C,1813", "Frederiksberg C,1814", "Frederiksberg C,1815", "Frederiksberg C,1816", "Frederiksberg C,1817", "Frederiksberg C,1818", "Frederiksberg C,1819", "Frederiksberg C,1820", "Frederiksberg C,1822", "Frederiksberg C,1823", "Frederiksberg C,1824", "Frederiksberg C,1825", "Frederiksberg C,1826", "Frederiksberg C,1827", "Frederiksberg C,1828", "Frederiksberg C,1829", "Frederiksberg C,1850", "Frederiksberg C,1851", "Frederiksberg C,1852", "Frederiksberg C,1853", "Frederiksberg C,1854", "Frederiksberg C,1855", "Frederiksberg C,1856", "Frederiksberg C,1857", "Frederiksberg C,1860", "Frederiksberg C,1861", "Frederiksberg C,1862", "Frederiksberg C,1863", "Frederiksberg C,1864", "Frederiksberg C,1865", "Frederiksberg C,1866", "Frederiksberg C,1867", "Frederiksberg C,1868", "Frederiksberg C,1870", "Frederiksberg C,1871", "Frederiksberg C,1872", "Frederiksberg C,1873", "Frederiksberg C,1874", "Frederiksberg C,1875", "Frederiksberg C,1876", "Frederiksberg C,1877", "Frederiksberg C,1878", "Frederiksberg C,1879", "Frederiksberg C,1900", "Frederiksberg C,1901", "Frederiksberg C,1902", "Frederiksberg C,1903", "Frederiksberg C,1904", "Frederiksberg C,1905", "Frederiksberg C,1906", "Frederiksberg C,1908", "Frederiksberg C,1909", "Frederiksberg C,1910", "Frederiksberg C,1911", "Frederiksberg C,1912", "Frederiksberg C,1913", "Frederiksberg C,1914", "Frederiksberg C,1915", "Frederiksberg C,1916", "Frederiksberg C,1917", "Frederiksberg C,1920", "Frederiksberg C,1921", "Frederiksberg C,1922", "Frederiksberg C,1923", "Frederiksberg C,1924", "Frederiksberg C,1925", "Frederiksberg C,1926", "Frederiksberg C,1927", "Frederiksberg C,1928", "Frederiksberg C,1950", "Frederiksberg C,1951", "Frederiksberg C,1952", "Frederiksberg C,1953", "Frederiksberg C,1954", "Frederiksberg C,1955", "Frederiksberg C,1956", "Frederiksberg C,1957", "Frederiksberg C,1958", "Frederiksberg C,1959", "Frederiksberg C,1960", "Frederiksberg C,1961", "Frederiksberg C,1962", "Frederiksberg C,1963", "Frederiksberg C,1964", "Frederiksberg C,1965", "Frederiksberg C,1966", "Frederiksberg C,1967", "Frederiksberg C,1970", "Frederiksberg C,1971", "Frederiksberg C,1972", "Frederiksberg C,1973", "Frederiksberg C,1974", "Herlev,2730", "Skovlunde,2740", "Ballerup,2750", "Måløv,2760", "Værløse,3500", "Glostrup,2600", "Brøndby,2605", "Albertslund,2620", "Hvidovre,2650", "Brøndby Strand,2660", "Dragør,2791", "Kongens Lyngby,2800", "Gentofte,2820", "Dyssegård,2870", "Charlottenlund,2920", "Klampenborg,2930", "Bagsværd,2880", "Høje Taastrup,800", "Taastrup,2630", "Returpost,2633", "Hedehusene,2640", "Roskilde,4000", "Virum,2830", "Holte,2840", "Ishøj,2635", "København V,1532", "København V,1533", "Vallensbæk,2625", "Vallensbæk Strand,2665", "Birkerød,3460", "Farum,3520", "Lynge,3540", "Hillerød,3400", "Allerød,3450", "Slangerup,3550", "Hørsholm,2970", "Kokkedal,2980", "Nivå,2990", "Humlebæk,3050", "Espergærde,3060", "Fredensborg,3480", "Kvistgård,3490", "Helsingør,3000", "Snekkersten,3070", "Tikøb,3080", "Hornbæk,3100", "Ålsgårde,3140", "Hellebæk,3150", "Græsted,3230", "Helsinge,3200", "Ølsted,3310", "Skævinge,3320", "Gørløse,3330", "Frederikssund,3600", "Vedbæk,2950", "Rungsted Kyst,2960", "Nærum,2850", "Skodsborg,2942", "Smørum,2765", "Ølstykke,3650", "Stenløse,3660", "Veksø Sjælland,3670", "Jægerspris,3630", "Skibby,4050", "Frederiksværk,3300", "Liseleje,3360", "Melby,3370", "Hundested,3390", "Dronningmølle,3120", "Vejby,3210", "Tisvildeleje,3220", "Gilleleje,3250", "Rønne,3700", "Aakirkeby,3720", "Nexø,3730", "Svaneke,3740", "Østermarie,3751", "Gudhjem,3760", "Allinge,3770", "Klemensker,3782", "Hasle,3790", "Greve,2670", "Karlslunde,2690", "Tune,4030", "Ringsted,4100", "Viby Sjælland,4130", "Borup,4140", "Jystrup Midtsj,4174", "Lejre,4320", "Køge,4600", "Lille Skensved,4623", "Bjæverskov,4632", "Herfølge,4681", "Tureby,4682", "Jyllinge,4040", "Gadstrup,4621", "Havdrup,4622", "Solrød Strand,2680", "Nykøbing Sj,4500", "Hørve,4534", "Fårevejle,4540", "Asnæs,4550", "Vig,4560", "Grevinge,4571", "Nørre Asmindrup,4572", "Højby,4573", "Rørvig,4581", "Sjællands Odde,4583", "Føllenslev,4591", "Stenlille,4295", "Nyrup,4296", "Holbæk,4300", "Hvalsø,4330", "Tølløse,4340", "Ugerløse,4350", "Kirke Eskilstrup,4360", "Store Merløse,4370", "Vipperød,4390", "Regstrup,4420", "Mørkøv,4440", "Jyderup,4450", "Snertinge,4460", "Svebølle,4470", "Svinninge,4520", "Gislinge,4532", "Herlufmagle,4160", "Faxe,4640", "Hårlev,4652", "Karise,4653", "Faxe Ladeplads,4654", "Rønnede,4683", "Holmegaard,4684", "Haslev,4690", "Tappernøje,4733", "Slagelse,4200", "Høng,4270", "Gørlev,4281", "Ruds Vedby,4291", "Dianalund,4293", "Kalundborg,4400", "Store Fuglede,4480", "Jerslev Sjælland,4490", "Sejerø,4592", "Eskebjerg,4593", "Ringsted,4105", "Fjenneslev,4173", "Sorø,4180", "Munke Bjergby,4190", "Korsør,4220", "Skælskør,4230", "Vemmelev,4241", "Boeslunde,4242", "Rude,4243", "Fuglebjerg,4250", "Dalmose,4261", "Sandved,4262", "Store Heddinge,4660", "Strøby,4671", "Klippinge,4672", "Rødvig Stevns,4673", "Kirke Såby,4060", "Kirke Hyllinge,4070", "Øster Ulslev,4894", "Errindlev,4895", "Nakskov,4900", "Harpelunde,4912", "Horslunde,4913", "Søllested,4920", "Maribo,4930", "Bandholm,4941", "Torrig L,4943", "Fejø,4944", "Nørreballe,4951", "Stokkemarke,4952", "Vesterborg,4953", "Holeby,4960", "Rødby,4970", "Dannemare,4983", "Glumsø,4171", "Næstved,4700", "Karrebæksminde,4736", "Lundby,4750", "Nykøbing F,4800", "Nørre Alslev,4840", "Stubbekøbing,4850", "Guldborg,4862", "Eskilstrup,4863", "Horbelev,4871", "Idestrup,4872", "Væggerløse,4873", "Gedser,4874", "Nysted,4880", "Toreby L,4891", "Kettinge,4892", "Sakskøbing,4990", "Præstø,4720", "Mern,4735", "Vordingborg,4760", "Kalvehave,4771", "Langebæk,4772", "Stensved,4773", "Stege,4780", "Borre,4791", "Askeby,4792", "Bogø By,4793"};
	
}
