package domain;

public class Test extends BaseEntity<Long> {
    private Algorithm algorithm;

    private int size;
    private int threads;
    private double time;
    private double score;

    private Long userID;

    public enum Algorithm{
        GAUSS_LEGENDRE("Gauss Legendre"),
        MONTE_CARLO("Monte Carlo"),
        SPIGOT("Spigot");

        public String url;

        Algorithm(String url) {
            this.url = url;
        }

        public static Algorithm fromString (String url) {
            return switch (url) {
                case "Spigot" -> SPIGOT;
                case "Monte Carlo" -> MONTE_CARLO;
                case "Gauss Legendre" -> GAUSS_LEGENDRE;
                default -> null;
            };
        }
    }

    public Test(Algorithm algorithm, int size, int threads, Long userID) {
        this.algorithm = algorithm;
        this.size = size;
        this.threads = threads;
        this.userID = userID;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
        calculateScore();
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }


    private void calculateScore(){
        switch ( algorithm ){
            case SPIGOT -> {
                this.score = size/time * 1000;
            }
            case GAUSS_LEGENDRE -> {
                this.score = size/time * 1000;
            }
            case MONTE_CARLO -> {
                this.score = size/time * 1000;
            }
        }
    }

    @Override
    public String toString() {
        return  "ID = " + userID + " " +
                algorithm + " " +
                size + " " +
                threads + " " +
                (int) time + " " +
                (int) score + " ";
    }
}
