import PropTypes from "prop-types";
import styled from 'styled-components';


function MatchThumbnailList({matchId,matchDate,myResult,myNickName,anotherNickname,myGoal,anotherGoal}){

    return(
        <GameListWrapper key={matchId}>
            <GameListBox>
                <li>매치날짜:{matchDate}</li>
                <li>내결과:{myResult}</li>
                <li>내 닉네임:{myNickName}</li>
                <li>상대 닉네임:{anotherNickname}</li>
                <li>내 골:{myGoal}</li>
                <li>상대 골:{anotherGoal}</li>
            </GameListBox>
        </GameListWrapper>
    );
}
export default MatchThumbnailList;

const GameListWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;

const VictoryBar = styled.div`
  width: 3px;
  margin-right: 0.25rem;
  border-radius: 5px 0px 0px 5px;
  transition: all 0.15s ease-in 0s;
  opacity: 0.8;
  background-color: ${props => {
  if(props.victory) {
    return '#49B4FF';
  } else {
    return 'rgb(255, 88, 89)';
  }
}};
`;

const GameListBox = styled.div`
  position: relative;
  left: 0;
  display: flex;
  // width: 660px;
  // height: 65px;
  background-color: #49B4FF;
  margin: 7px 0;
  border-radius: 5px;
  &:hover {
    transition: left ease 0.5s;
    left: -6px;
  };
  &:hover ${VictoryBar} {
    opacity: 1;
    background-color: ${props => {
  if(props.victory) {
    return '#24E8CC'
  } else {
    return '#FF5859'
    }}};
  };
`;