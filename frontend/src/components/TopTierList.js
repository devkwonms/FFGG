import { Box, Typography } from "@mui/material";
import PropTypes from "prop-types";

function TopTierList({matchType,division,achievementDate,divisionImgUrl}){

    return(
        <Box>
            <img src={divisionImgUrl}></img>
            <Typography >{matchType}</Typography>
            <Typography>{division}</Typography>
            <Typography>{achievementDate}</Typography>
        </Box>
    );
}
TopTierList.propTypes = {
    matchType: PropTypes.string.isRequired,
    division: PropTypes.string.isRequired,
    achievementDate: PropTypes.string.isRequired,
}
export default TopTierList;