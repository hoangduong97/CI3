/**
 * Created by AsusA42F on 3/11/2016.
 */
public class GiftManager {
    private Gift gift = new Gift(150, 100);
    private static GiftManager ourInstance = new GiftManager();

    public static GiftManager getInstance() {
        return ourInstance;
    }

    private GiftManager() {
    }

    public Gift getGift() {
        return gift;
    }
}
