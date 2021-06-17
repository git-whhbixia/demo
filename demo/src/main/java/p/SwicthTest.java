package p;

/**
 * Create by Hercules
 * 2021-06-11 14:20
 */
public class SwicthTest {

    public int getResult(int k) {

        int m = 0;
        switch (k) {
            case 1:
                m = 1;
                break;
            case 2:
                m = 2;
                break;
            case 3:
                m = 3;
                break;
            case 4:
                m = 4;
                break;
            default:
                m = 5;
                break;
        }
        return m;
    }
}
