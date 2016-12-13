package andrii.kclusters;

/**
 * Created by Andrii_Tugai on 12/12/2016.
 */
public class KClusterDriver {
    public static void main(String[] args) {
        KCluster kCluster = new KCluster();
        kCluster.init("clustering1.txt");

        int maxSpasing = kCluster.getMaxSpacing(4);
        System.out.println("Max-Spacing for k = 4 : " + maxSpasing);

    }
}
