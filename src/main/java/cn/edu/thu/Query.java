package cn.edu.thu;


import com.datastax.driver.core.*;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Cluster;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by RuiLei on 2017/11/23.
 */
public class Query {

    private static String ks = "replica";
    private static String node = "127.0.0.1";
    private static String cf = "data"; // work with InsertData
    //private static int range = 1000;

    public static void main(String[] args) throws Exception {
        //CassandraCluster cluster = CassandraCluster.getInstance(nodes);
        Cluster cluster = Cluster.builder().addContactPoint(node).build();
        Session session = cluster.connect(ks);

        Random random = new Random(10);


        double time = 0;
        int rep=1000;
        for (int i = 0; i < rep; i++) {//repetition
                String selectRangeCql1 = "select c1,c2,c3,c4  from " + ks + "." + cf + " WHERE c1 = 1 AND "
                        +"c2>=2 AND c2<=3 AND c3>=8 AND C3<=9 ALLOW FILTERING"; // note here don't select c5
                long elapsed = System.nanoTime();
                ResultSet rs1 = session.execute(selectRangeCql1);
                elapsed = System.nanoTime() - elapsed;
                time += elapsed / (double) Math.pow(10, 9);
                //System.out.println(time);
        }
        time = time / rep * Math.pow(10, 9);
        session.close();

        System.out.println(time);
        System.out.println("now to next part");
        System.out.println("now to next part");
        System.out.println("now to next part");

        cluster.close();
    }
}
