import java.util.List;

public class ExclusiveTimeOfFunctions {
    private static final int START = 0;
    private static final int END = 1;

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] times = new int[n];
        int top = 0;
        Event[] callStack = new Event[logs.size()];
        Event current = new Event(0, 0, 0);
        for (String record : logs) {
            Event event = parse(record);
            times[current.id] += (event.time - current.time);
            if (event.code == START) {
                callStack[top++] = current;
                current = event;
            } else {
                times[current.id]++;
                int nextTime = event.time + 1;
                current = callStack[--top];
                current.time = nextTime;
            }
        }
        return times;
    }

    private static Event parse(String record) {
        int index = 0;
        int id = 0;
        while (record.charAt(index) != ':') {
            id = (id * 10) + (record.charAt(index++) - '0');
        }
        int mode;
        if (record.charAt(++index) == 's') {
            mode = START;
            index += 6;
        } else {
            mode = END;
            index += 4;
        }
        int time = 0;
        while (index < record.length()) {
            time = (time * 10) + (record.charAt(index++) - '0');
        }
        return new Event(id, mode, time);
    }

    private static class Event {
        public final int id;
        public final int code;
        public int time;
        public Event(int id, int code, int time) {
            this.id = id;
            this.code = code;
            this.time = time;
        }
    }
    public static void main(String[] args){
        long test = Math.abs((long)Integer.MIN_VALUE);
    }
}
