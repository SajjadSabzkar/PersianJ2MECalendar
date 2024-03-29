package Reverse;

import java.util.Calendar;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStoreException;

public class Main extends MIDlet implements CommandListener {

    private static Command mDoneCommand = null;
    public static AnimationThread animationThread = null;
    public static DateInfo currentGhamariDate = null;
    public static DateInfo currentMiladiDate = null;
    public static DateInfo currentShamsiDate = null;
    public static int dayOFShamsiYear = 0;
    public static Display display = null;
    public static Main instance = null;
    public static KeyboradThread keyboardThread = null;
    public static LoadingCanvas loading = null;
    public static MainScreenCanvas mainScreen = null;
    public static int memoInstallState = 0;
    public static byte[] memoState = null;
    public static TextBox memoTextBox = null;
    public static int projectHeight = 0;
    public static int projectWidth = 0;
    public static final boolean readyForRelease = true;
    public static DateInfo todayDate = null;
    public static final String version = "T1403v1";
   
    Canvas setupCanvas;

    private RecordStoreManager recordStoreManager = null;
    public boolean secret = false;
    public static shamsi mShamsi;
    public static shamsi mConvertMiladi;
    public static shamsi mConvertHijri;
    

    public void calendarCorrection() {
        
        Calendar currentCalendar = Calendar.getInstance();
        int i = currentCalendar.get(5);
        int i2 = currentCalendar.get(2);
        int i3 = currentCalendar.get(1);
        
        mShamsi = new shamsi();
        mConvertMiladi = new shamsi();
        mConvertHijri= new shamsi();
       

        mShamsi.GregorianToPersian(
                i3, i2 + 1, i);

        todayDate = new DateInfo(currentCalendar.get(5), currentCalendar.get(2), currentCalendar.get(1), currentCalendar.get(7));
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == mDoneCommand) {
            if (memoTextBox.size() > 0) {
                memoState[dayOFShamsiYear + 1] = 49;
            } else {
                memoState[dayOFShamsiYear + 1] = 48;
            }
            this.recordStoreManager = new RecordStoreManager();
            try {
                recordStoreManager.recordStore.setRecord(367, memoState, 0, memoState.length);
                recordStoreManager.recordStore.closeRecordStore();
            } catch (RecordStoreException e) {
            }
            new RecordStoreThread(dayOFShamsiYear + 1, CharacterByteConverter.convertStringToBytes(memoTextBox.getString()));
            display.setCurrent(new SplashScreenCanvas(this));
        }
    }

    public void destroyApp(boolean z) {
    }

    public Canvas get_CrackedScreen() {
        CrackScreenCanvas crackScreenCanvas = new CrackScreenCanvas(this);
        crackScreenCanvas.setFullScreenMode(true);
        return crackScreenCanvas;
    }

    public Canvas get_Setup() {
        SetupCanvas setupCanvas = new SetupCanvas(this);
        setupCanvas.setFullScreenMode(true);
        return setupCanvas;
    }

    public Canvas get_helpScreen() {
        HelpCanvas dVar = new HelpCanvas(this);
        dVar.setFullScreenMode(true);
        return dVar;
    }

    public void pauseApp() {
    }

    public void quit() {
        destroyApp(true);
        notifyDestroyed();
    }

    public void startApp() {
        instance = this;
        display = Display.getDisplay(this);
        calendarCorrection();

        currentShamsiDate = new DateInfo();

        currentGhamariDate = new DateInfo();

        currentMiladiDate = new DateInfo();

        memoTextBox = new TextBox("یادداشت", "", 64, 0);
        mDoneCommand = new Command("Done", 4, 1);
        memoTextBox.addCommand(mDoneCommand);
        memoTextBox.setCommandListener(this);
        loading = new LoadingCanvas();
      
        this.recordStoreManager = new RecordStoreManager();
        if (this.recordStoreManager.hasIncompleteRecords()) {
            this.recordStoreManager.closeAndDeleteRecordStore();
            this.setupCanvas = get_Setup();
            display.setCurrent(this.setupCanvas);
            this.recordStoreManager = new RecordStoreManager();
            this.recordStoreManager.createIncompleteRecords();
        } else {
            this.recordStoreManager.closeRecorder();
        }
        this.recordStoreManager = new RecordStoreManager();
        memoState = this.recordStoreManager.getIncompleteRecords();
        display.setCurrent(loading);
        EventGraphicsManager.m17a();
        DateManager.updateDates();
        mainScreen = new MainScreenCanvas();
        keyboardThread = new KeyboradThread(0);
        animationThread = new AnimationThread(projectWidth);
        display.setCurrent(get_helpScreen());
    }
}
