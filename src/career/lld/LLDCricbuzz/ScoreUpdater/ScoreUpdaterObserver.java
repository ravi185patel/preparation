package target2023.LLD.LowLevelDesign.LLDCricbuzz.ScoreUpdater;


import target2023.LLD.LowLevelDesign.LLDCricbuzz.Inning.BallDetails;

public interface ScoreUpdaterObserver {

    public void update(BallDetails ballDetails);
}
