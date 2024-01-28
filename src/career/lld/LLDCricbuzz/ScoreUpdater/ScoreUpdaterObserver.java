package career.lld.LLDCricbuzz.ScoreUpdater;


import career.lld.LLDCricbuzz.Inning.BallDetails;

public interface ScoreUpdaterObserver {

    public void update(BallDetails ballDetails);
}
