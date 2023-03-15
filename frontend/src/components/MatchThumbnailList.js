import { Stack, Typography } from "@mui/material";
import PropTypes from "prop-types";
import styled from "styled-components";

function MatchThumbnailList({
  matchId,
  matchDate,
  myResult,
  myNickName,
  anotherNickname,
  myGoal,
  anotherGoal,
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
    <GameListWrapper key={matchId}>
      <GameListBox victory={myResult === {myResult}} backgroundColor={getBackgroundColor()}>
          <Stack direction={"row"} justifyContent={"space-between"} mt={2}>
          <Stack ml={2} width={"80%"}>
          <Typography>{matchDate}</Typography>
          </Stack>
          <Stack width={"100%"}>
              <Typography>{myNickName} {myGoal} : {anotherGoal} {anotherNickname}</Typography></Stack>
          </Stack>
      </GameListBox>
    </GameListWrapper>
  );
}

export default MatchThumbnailList;

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

