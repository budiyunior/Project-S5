package com.ifcodedeveloper.cakwangcafe.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ifcodedeveloper.cakwangcafe.R;
import com.ifcodedeveloper.cakwangcafe.model.cart.Cart;
import com.ifcodedeveloper.cakwangcafe.model.cart.GetCart;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.GetOrderProduct;
import com.ifcodedeveloper.cakwangcafe.model.orderProduct.OrderProduct;
import com.ifcodedeveloper.cakwangcafe.model.transaction.Transaction;
import com.ifcodedeveloper.cakwangcafe.print.UnicodeFormatter;
import com.ifcodedeveloper.cakwangcafe.rest.ApiClient;
import com.ifcodedeveloper.cakwangcafe.rest.ApiInterface;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrintActivity extends AppCompatActivity implements Runnable {
    protected static final String TAG = "TAG";
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    Button mScan, mPrint, mDisc;
    BluetoothAdapter mBluetoothAdapter;
    private UUID applicationUUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ProgressDialog mBluetoothConnectProgressDialog;
    private BluetoothSocket mBluetoothSocket;
    BluetoothDevice mBluetoothDevice;
    TextView tv_nama,tv_produk;
    ArrayList<Cart> orderList = new ArrayList<>();
    String nama, harga, jumlah, totalHarga, id_transaksi;
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences, sharedPreferences2;
    String pelanggan, meja, jam, tanggal, total, newFormat, id_trans, pass, nama_pengguna, newFormatTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        tv_nama = findViewById(R.id.tv_nama);
        tv_produk = findViewById(R.id.tv_produk);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPreferences = getSharedPreferences("pelanggan", Context.MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("remember", Context.MODE_PRIVATE);
        Bundle bundle = getIntent().getExtras();
        id_transaksi = bundle.getString("id_transaksi");
//        id_transaksi = sharedPreferences.getString("id_transaksi", "0");
        pass = sharedPreferences2.getString("password_wifi", "0");
        nama_pengguna = sharedPreferences2.getString("nama_pengguna", "kasir");
        tv_nama.setText(nama_pengguna);

//        if(nama_pengguna.length() >= 5){
//            nama_penggunaku = nama_pengguna.substring(0,5);
//        }else {
//            nama_penggunaku = nama_pengguna.substring(0,5);
//        }

        Log.e("cek cek", "onCreate: " + id_transaksi + pass + tv_nama.getText().toString());
        ShowCart();
        GetTrans();

        mScan = findViewById(R.id.Scan);
        mScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View mView) {
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter == null) {
                    Toast.makeText(PrintActivity.this, "Message1", Toast.LENGTH_SHORT).show();
                } else {
                    if (!mBluetoothAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(
                                BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent,
                                REQUEST_ENABLE_BT);
                    } else {
                        ListPairedDevices();
                        Intent connectIntent = new Intent(PrintActivity.this, DeviceListActivity.class);
                        startActivityForResult(connectIntent,
                                REQUEST_CONNECT_DEVICE);
                    }
                }
            }
        });

        mPrint = findViewById(R.id.mPrint);
        mPrint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View mView) {
                Thread t = new Thread() {
                    public void run() {
                        try {
                            OutputStream os = mBluetoothSocket
                                    .getOutputStream();
                            String BILL = "";
                            BILL =        "          CAK WANG CAFE       \n";
                            BILL = BILL + "     Rest Area Jubung Jember   \n";
                            BILL = BILL + "Password Wi-Fi: " + pass + "\n";
//                            BILL = BILL + String.format("%1$-28s %2$9s ", "Kode Transaksi: " + id_trans, "Kasir: " + tv_nama.getText().toString());
                            BILL = BILL + String.format("Kode Transaksi: " + id_trans+"\n");
                            BILL = BILL + "--------------------------------\n";
                            BILL = BILL + String.format("%1$-14s %2$5s %3$11s", pelanggan + "/" + meja, newFormatTime, newFormat);
                            BILL = BILL + "--------------------------------\n";
                            BILL = BILL + String.format("%1$-18s%2$5s %3$2s%4$5s", "Nama Produk", "Harga", "Qty", "SubT") + "\n";
                            for (int i = 0; i < orderList.size(); i++) {
                                nama = orderList.get(i).getNama_produk();
                                harga = orderList.get(i).getHarga_satuan();
                                jumlah = orderList.get(i).getJumlah();
                                totalHarga = orderList.get(i).getSub_total();
                                String s = nama.substring(0, Math.min(5, nama.length() - 1));
                                Log.e("test", "nama " + s + "harga " + harga + "jumlah " + jumlah + "total " + totalHarga);
                                BILL = BILL + String.format("%1$-18s%2$5s %3$2s %4$5s", nama, harga, jumlah, totalHarga) + "\n";
                            }
                            BILL = BILL + "              ------------------\n";
                            BILL = BILL + String.format("%1$-14s%2$8s  %3$8s", "", "Total : ", total);
                            BILL = BILL + "          Terima Kasih          \n";
                            BILL = BILL + "     Silahkan Datang Kembali    \n";
                            BILL = BILL + "\n";
                            BILL = BILL + "\n";
                            BILL = BILL + "\n";
                            os.write(BILL.getBytes());
                            Log.d("test", "hasil " + BILL);
                            //This is printer specific code you can comment ==== > Start

                            // Setting height
                            int gs = 29;
                            os.write(intToByteArray(gs));
                            int h = 104;
                            os.write(intToByteArray(h));
                            int n = 162;
                            os.write(intToByteArray(n));

                            // Setting Width
                            int gs_width = 29;
                            os.write(intToByteArray(gs_width));
                            int w = 119;
                            os.write(intToByteArray(w));
                            int n_width = 2;
                            os.write(intToByteArray(n_width));


                        } catch (Exception e) {
                            Log.e("MainActivity", "Exe ", e);
                        }
                    }
                };
                t.start();
            }
        });

//        mDisc = findViewById(R.id.dis);
//        mDisc.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View mView) {
//                if (mBluetoothAdapter != null)
//                    mBluetoothAdapter.disable();
//            }
//        });

    }// onCreate

    public void ShowCart() {
        Call<GetCart> ItemCall = mApiInterface.getCart(id_transaksi);
        ItemCall.enqueue(new Callback<GetCart>() {
            @Override
            public void onResponse(Call<GetCart> call, Response<GetCart>
                    response) {
                orderList = response.body().getListDataCart();

                Log.d("Retrofit Get", "Jumlah data Item: " + String.valueOf(orderList.size()));
                for (int i = 0; i < orderList.size(); i++) {
                    id_trans = orderList.get(i).getId_transaksi();
                    nama = orderList.get(i).getNama_produk();
                    harga = orderList.get(i).getHarga_satuan();
                    jumlah = orderList.get(i).getJumlah();
                    totalHarga = orderList.get(i).getSub_total();
                    Log.e("test", "nama " + nama + "harga " + harga + "jumlah " + jumlah + "total " + totalHarga);
                }
            }

            @Override
            public void onFailure(Call<GetCart> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    void GetTrans() {
        Call<Transaction> TransCall = mApiInterface.getTrans(id_transaksi);
        TransCall.enqueue(new Callback<Transaction>() {

            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                pelanggan = response.body().getNama_pelanggan();
                meja = response.body().getNo_meja();
                jam = response.body().getJam();
                tanggal = response.body().getTanggal();
                total = response.body().getTotal_harga();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date testDate = null;
                try {
                    testDate = sdf.parse(tanggal);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                newFormat = formatter.format(testDate);

                SimpleDateFormat sdt = new SimpleDateFormat("HH:mm:ss");
                Date testTime = null;
                try {
                    testTime = sdt.parse(jam);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                SimpleDateFormat formattime = new SimpleDateFormat("HH:mm");
                newFormatTime = formattime.format(testTime);

//                tv_pelanggan.setText(pelanggan);
//                tv_meja.setText("Meja No." + meja);
//                tv_jam.setText(jam);
//                tv_tanggal.setText(newFormat);
//                tv_total_harga.setText("Rp. " + total);
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        try {
            if (mBluetoothSocket != null)
                mBluetoothSocket.close();
        } catch (Exception e) {
            Log.e("Tag", "Exe ", e);
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if (mBluetoothSocket != null)
                mBluetoothSocket.close();
        } catch (Exception e) {
            Log.e("Tag", "Exe ", e);
        }
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onActivityResult(int mRequestCode, int mResultCode,
                                 Intent mDataIntent) {
        super.onActivityResult(mRequestCode, mResultCode, mDataIntent);

        switch (mRequestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (mResultCode == Activity.RESULT_OK) {
                    Bundle mExtra = mDataIntent.getExtras();
                    String mDeviceAddress = mExtra.getString("DeviceAddress");
                    Log.v(TAG, "Coming incoming address " + mDeviceAddress);
                    mBluetoothDevice = mBluetoothAdapter
                            .getRemoteDevice(mDeviceAddress);
                    mBluetoothConnectProgressDialog = ProgressDialog.show(this,
                            "Connecting...", mBluetoothDevice.getName() + " : "
                                    + mBluetoothDevice.getAddress(), true, false);
                    Thread mBlutoothConnectThread = new Thread(this);
                    mBlutoothConnectThread.start();
                    // pairToDevice(mBluetoothDevice); This method is replaced by
                    // progress dialog with thread
                }
                break;

            case REQUEST_ENABLE_BT:
                if (mResultCode == Activity.RESULT_OK) {
                    ListPairedDevices();
                    Intent connectIntent = new Intent(PrintActivity.this,
                            DeviceListActivity.class);
                    startActivityForResult(connectIntent, REQUEST_CONNECT_DEVICE);
                } else {
                    Toast.makeText(PrintActivity.this, "Message", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void ListPairedDevices() {
        Set<BluetoothDevice> mPairedDevices = mBluetoothAdapter
                .getBondedDevices();
        if (mPairedDevices.size() > 0) {
            for (BluetoothDevice mDevice : mPairedDevices) {
                Log.v(TAG, "PairedDevices: " + mDevice.getName() + "  "
                        + mDevice.getAddress());
            }
        }
    }

    public void run() {
        try {
            mBluetoothSocket = mBluetoothDevice
                    .createRfcommSocketToServiceRecord(applicationUUID);
            mBluetoothAdapter.cancelDiscovery();
            mBluetoothSocket.connect();
            mHandler.sendEmptyMessage(0);
        } catch (IOException eConnectException) {
            Log.d(TAG, "CouldNotConnectToSocket", eConnectException);
            closeSocket(mBluetoothSocket);
            return;
        }
    }

    private void closeSocket(BluetoothSocket nOpenSocket) {
        try {
            nOpenSocket.close();
            Log.d(TAG, "SocketClosed");
        } catch (IOException ex) {
            Log.d(TAG, "CouldNotCloseSocket");
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mBluetoothConnectProgressDialog.dismiss();
            Toast.makeText(PrintActivity.this, "DeviceConnected", Toast.LENGTH_SHORT).show();
        }
    };

    public static byte intToByteArray(int value) {
        byte[] b = ByteBuffer.allocate(4).putInt(value).array();

        for (int k = 0; k < b.length; k++) {
            System.out.println("Selva  [" + k + "] = " + "0x"
                    + UnicodeFormatter.byteToHex(b[k]));
        }

        return b[3];
    }

    public byte[] sel(int val) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putInt(val);
        buffer.flip();
        return buffer.array();
    }
}

