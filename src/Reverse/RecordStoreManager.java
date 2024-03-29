package Reverse;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/* renamed from: s */
public final class RecordStoreManager {

    RecordStore recordStore = null;

    public RecordStoreManager() {
        try {
            this.recordStore = RecordStore.openRecordStore(Main.version, true);
        } catch (RecordStoreException e) {
        }
    }

    public DateManager getRecord(int recordId) {
        try {
            byte[] data = recordStore.getRecord(recordId);
            return new DateManager(recordId, data);
        } catch (RecordStoreNotOpenException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidRecordIDException e) {
            e.printStackTrace();
            return null;
        } catch (RecordStoreException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                recordStore.closeRecordStore();
            } catch (Exception e) {
            }
        }
    }

    public final void closeAndDeleteRecordStore() {
        try {
            this.recordStore.closeRecordStore();
            RecordStore.deleteRecordStore(Main.version);
        } catch (RecordStoreException e) {
        }
    }

    public final boolean hasIncompleteRecords() {
        try {
            return this.recordStore.getNumRecords() != 367;
        } catch (RecordStoreException e) {
            return false;
        }
    }

    public final byte[] getIncompleteRecords() {
        byte[] bArr = null;
        try {
            bArr = this.recordStore.getRecord(367);
            this.recordStore.closeRecordStore();
            return bArr;
        } catch (RecordStoreException e) {
            return bArr;
        }
    }

    public final void createIncompleteRecords() {
        int i = 1;
        byte[] bytes = "".getBytes();
        try {
            if (this.recordStore.getNumRecords() != 367) {
                while (i < 367) {
                    Main.memoInstallState = i;
                    this.recordStore.addRecord(bytes, 0, bytes.length);
                    i++;
                    Main.instance.setupCanvas.repaint();
                }
                byte[] bArr = new byte[367];
                for (int i2 = 0; i2 < 367; i2++) {
                    bArr[i2] = 48;
                }
                this.recordStore.addRecord(bArr, 0, bArr.length);
            }
            this.recordStore.closeRecordStore();
        } catch (RecordStoreException e) {
        }
    }

    public final void closeRecorder() {
        try {
            this.recordStore.closeRecordStore();
        } catch (RecordStoreException e) {
        }
    }
}
