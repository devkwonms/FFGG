import PropTypes from "prop-types";

function TopTierList({matchType,division,achievementDate}){

    return(
        <div key={matchType}>
            <h4>매치타입:{matchType}</h4>
            <h4>티어:{division}</h4>
            <h4>최고티어달성일:{achievementDate}</h4>
            </div>
    );
}
TopTierList.propTypes = {
    matchType: PropTypes.string.isRequired,
    division: PropTypes.string.isRequired,
    achievementDate: PropTypes.string.isRequired,
}
export default TopTierList;