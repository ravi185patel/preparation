package target2023.LLD.LowLevelDesign.LLDCricbuzz.Team;


import target2023.LLD.LowLevelDesign.LLDCricbuzz.Inning.BallDetails;
import target2023.LLD.LowLevelDesign.LLDCricbuzz.Inning.OverDetails;
import target2023.LLD.LowLevelDesign.LLDCricbuzz.Team.Player.PlayerDetails;

public class Wicket {

    public WicketType wicketType;
    public PlayerDetails takenBy;
    public OverDetails overDetail;
    public BallDetails ballDetail;

    public Wicket(WicketType wicketType, PlayerDetails takenBy, OverDetails overDetail, BallDetails ballDetail){

        this.wicketType = wicketType;
        this.takenBy = takenBy;
        this.overDetail = overDetail;
        this.ballDetail = ballDetail;
    }
}
