package cn.edu.thu;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class ClusterCase {
    private static String ks = "clu";
    private static String cf = "dat";
    private static String nodes = "192.168.10.10";
    public static void main(String[] args) throws Exception {
        CassandraCluster cluster = CassandraCluster.getInstance(nodes);
        //Cluster cluster = Cluster.builder().addContactPoint(node).build();
        String createCf = "CREATE TABLE IF NOT EXISTS " + ks + "." + cf + " (" + "c1 int," + "c2 int,"
                + "c3 int, c4 int, c5 int, " + "PRIMARY KEY(c1,c2,c3)" + ");";
        cluster.dropKeyspace(ks);
        cluster.createKeyspace(ks, "SimpleStrategy", 1); // no USE is ok???
        Session session = cluster.getSession();
        session.execute(createCf);

        String cql = "select * from " + ks + "." + cf+";";
        session.execute(cql);

        cluster.close();
    }

}
