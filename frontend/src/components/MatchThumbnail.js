import { Accordion, Stack, Typography } from "@mui/material";
import PropTypes from "prop-types";
import styled from "styled-components";

import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

function MatchThumbnail({
  matchId,
  matchDate,
  myResult,
  leftNickName,
  rightNickname,
  leftScore,
  rightScore,
}) {
  const getBackgroundColor = () => {
    switch (myResult) {
      case "승":
        return "#49B4FF";
      case "패":
        return "rgb(255, 88, 89)";
      case "무":
        return "#D8D8D8";
      default:
        return "#49B4FF";
    }
  };

  return (
    <Accordion>
      
    <GameListWrapper key={matchId}>
    <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1a-content"
          id="panel1a-header"
        >
      <GameListBox victory={myResult === {myResult}} backgroundColor={getBackgroundColor()}>
          <Stack direction={"row"} justifyContent={"space-between"} mt={2}>
            <Stack ml={2} width={"80%"}>
              <Typography>{matchDate}</Typography>
            </Stack>
              <Stack width={"100%"}>
                <Typography>{leftNickName} {leftScore} : {rightScore} {rightNickname}</Typography>
              </Stack>
          </Stack>
      </GameListBox>
      </AccordionSummary>
      <AccordionDetails>
      <Typography>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse
            malesuada lacus ex, sit amet blandit leo lobortis eget.
          </Typography>
      </AccordionDetails>
    </GameListWrapper>
    
    </Accordion>
  );
}

export default MatchThumbnail;

const GameListWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;

const GameListBox = styled.div`
  position: relative;
  left: 0;
  width: 100%;
  height: 65px;
  background-color: ${(props) => props.backgroundColor};
  margin: 7px 0;
  border-radius: 5px;
`;

const GameListInfo = styled.ul`
  position: relative;
  list-style: none;
  padding-left: 10px;
  font-size: 1.2rem;
  color: #ffffff;
  display: flex;
  align-items: center;

  div {
    margin-bottom: 0.5rem;
  }
`;

