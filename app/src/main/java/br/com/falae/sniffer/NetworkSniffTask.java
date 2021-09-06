//package br.com.falae.sniffer;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.net.wifi.WifiInfo;
//import android.net.wifi.WifiManager;
//import android.os.AsyncTask;
//import android.text.format.Formatter;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import java.lang.ref.WeakReference;
//import java.net.InetAddress;
//import java.util.ArrayList;
//import java.util.List;
//
//import br.com.falae.R;
//import br.com.falae.activities.chat.ChatActivity;
//import br.com.falae.adapters.UserListAdapter;
//import br.com.falae.factories.UserFactory;
//import br.com.falae.models.User;
//import br.com.falae.singletons.Info;
//
//public class NetworkSniffTask extends AsyncTask<Void, Void, ArrayList<String>> {
//
//    private static final String TAG = "nstask";
//    private ArrayList<String> ips = new ArrayList<>();
//
//    private WeakReference<Context> mContextRef;
//    private View v;
//
//    public NetworkSniffTask(Context context, View v) {
//        mContextRef = new WeakReference<Context>(context);
//        this.v = v;
//    }
//
//    @Override
//    protected ArrayList doInBackground(Void... voids) {
//        Log.d(TAG, "Let's sniff the network");
//
//        try {
//            Context context = mContextRef.get();
//
//            if (context != null) {
//
//                ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//                WifiManager wm = (WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//
//                WifiInfo connectionInfo = wm.getConnectionInfo();
//                int ipAddress = ((WifiInfo) connectionInfo).getIpAddress();
//                String ipString = Formatter.formatIpAddress(ipAddress);
//
//
//                Log.d(TAG, "activeNetwork: " + String.valueOf(activeNetwork));
//                Log.d(TAG, "ipString: " + String.valueOf(ipString));
//
//                String prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1);
//                Log.d(TAG, "prefix: " + prefix);
//
//                for (int i = 0; i < 255; i++) {
//                    String testIp = prefix + String.valueOf(i);
//
//                    InetAddress address = InetAddress.getByName(testIp);
//                    boolean reachable = address.isReachable(1000);
//                    String hostName = address.getCanonicalHostName();
//                    Log.i(TAG, String.valueOf(i));
//                    if (reachable){
//                        Log.i(TAG, "Host: " + String.valueOf(hostName) + "(" + String.valueOf(testIp) + ") is reachable!");
//                        this.ips.add(testIp);
//                    }
//                }
//            }
//
//            return this.ips;
//        } catch (Throwable t) {
//            Log.e(TAG, "Well that's not good.", t);
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(ArrayList<String> ips) {
////        super.onPostExecute(ips);
//
//
//        final List<User> onlineUsers = UserFactory.getMockUsers(ips.size());
//
//        ListView listView = (ListView) v.findViewById(R.id.list_view);
//
//        final Activity a = ( Activity)  this.mContextRef.get();
//        listView.setAdapter(new UserListAdapter(a, R.layout.user_list, onlineUsers));
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Info i = Info.getInstance();
//                i.putInfo("CHOOSENCHAT", onlineUsers.get(position));
//
//                Intent chatActivity = new Intent(a, ChatActivity.class);
//                a.startActivity(chatActivity);
//
//            }
//        });
//
//
//    }
//}