import PropTypes from "prop-types";

function MatchThumbnailList({matchId}){

    return(
        <div key={matchId}>
            <h4>매치ID:{matchId}</h4>
        </div>
    );
}
MatchThumbnailList.propTypes = {
    matchId: PropTypes.string.isRequired,
  };
export default MatchThumbnailList;