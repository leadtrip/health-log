package wood.mike.healthlog.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class FeelingLabelProvider {

    public static Map<Feeling, String> getFeelingLabels() {
        Map<Feeling, String> labels = new LinkedHashMap<>();
        labels.put(Feeling.GREAT, "😄 Great");
        labels.put(Feeling.GOOD, "🙂 Good");
        labels.put(Feeling.OKAY, "😐 Okay");
        labels.put(Feeling.BAD, "🙁 Bad");
        labels.put(Feeling.REALLY_BAD, "😩 Really Bad");
        return labels;
    }
}
