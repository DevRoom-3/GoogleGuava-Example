package Basic;

import com.google.common.base.Preconditions;

import java.util.logging.Logger;

/**
 * create by chenjiayang on 2018/9/14
 */
public class PreconditionsTest {

    private static final Logger logger = Logger.getLogger(Preconditions.class.getName());

    public static void main(String[] args) throws Exception {
        int i = 10;
        try {
            Preconditions.checkArgument(i < 10, "i is greater than 10");
            Preconditions.checkArgument(i < 10, "i is greater than 10, i is %s", i);
        } catch (IllegalArgumentException e) {
            logger.info("IllegalArgumentException e=" + e.getMessage());
        }

        String str = null;
        try {
            Preconditions.checkNotNull(str);
        } catch (NullPointerException e) {
            logger.info("NullPointerException e=" + e.getMessage());
        }

        try {
            Preconditions.checkState(str == null);
        } catch (IllegalStateException e) {
            logger.info("IllegalStateException e=" + e.getMessage());
        }
    }
}
