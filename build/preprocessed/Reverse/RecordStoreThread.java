package Reverse;

import javax.microedition.rms.RecordStoreException;

public final class RecordStoreThread extends Thread {

    private int recordId;

    private RecordStoreManager recordStoreManager ;

    private byte[] recordData;

    public RecordStoreThread(int recordId, byte[] recordData) {
        this.recordId = recordId;
        this.recordData = recordData;
        start();
    }

    public final void run() {
        this.recordStoreManager  = new RecordStoreManager();
        RecordStoreManager manager  = this.recordStoreManager ;
        int id  = this.recordId;
        byte[] bArr = this.recordData;
        try {
            manager .recordStore.setRecord(id , bArr, 0, bArr.length);
            manager .recordStore.closeRecordStore();
        } catch (RecordStoreException e) {
           e.printStackTrace();
        }
    }
}
