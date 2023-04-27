import styled from 'styled-components';
import Player from "./Player";

function SoccerField({matchInfo}){
  // console.log(matchInfo);
  
  const leftMatchInfo = matchInfo[0];
  const rightMatchInfo = matchInfo[1];

  console.log(leftMatchInfo)
  console.log(rightMatchInfo)

  return (
    <Field>
        <Player number={1} position={0} />
        <Player number={2} position={1} />
        <Player number={3} position={2} />
        <Player number={4} position={3} />
        <Player number={5} position={4} />
        <Player number={6} position={5} />
        <Player number={7} position={6} />
        <Player number={8} position={7} />
        <Player number={9} position={8} />
        <Player number={10} position={9} />
        <Player number={11} position={10} />
        <Player number={12} position={11} />
        <Player number={13} position={12} />
        <Player number={14} position={13} />
        <Player number={15} position={14} />
        <Player number={16} position={15} />
        <Player number={17} position={16} />
        <Player number={18} position={17} />
        <Player number={19} position={18} />
        <Player number={20} position={19} />
        <Player number={21} position={20} />
        <Player number={22} position={21} />
        <Player number={23} position={22} />
        <Player number={24} position={23} />
        <Player number={25} position={24} />
        <Player number={26} position={25} />
        <Player number={27} position={26} />
        <Player number={28} position={27} />
      <FieldLines />
      <FieldLines />
      <FieldLines />
      <CenterCircle />
      <CenterSpot />
      <LeftPenaltyBox>
        <Arc isLeft />
        
        <LeftPenaltyBoxContent />
      </LeftPenaltyBox>
      <RightPenaltyBox>
        <Arc />
        <RightPenaltyBoxContent />
      </RightPenaltyBox>
    </Field>
  );
};
export default SoccerField;
const Field = styled.div`
  background-color: #4caf50; /* 잔디색 배경색 */
  height: 600px;
  width: 100%;
  position: relative;
  overflow: hidden;
`;

const CenterSpot = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 6px;
  height: 6px;
  background-color: #fff;
  border-radius: 50%;
`;

const CenterCircle = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 130px;
  height: 130px;
  border-radius: 50%;
  border: 2px solid #fff;
  background-color: transparent;
  box-sizing: content-box;

  &:before {
    content: '';
    position: absolute;
    top: -2px;
    left: -2px;
    width: 100%;
    height: 100%;
    border-radius: inherit;
    border: 2px solid #fff;
  }
`;
const FieldLines = styled.div`
  height: 600px;
  width: 100%;
  position: absolute;
  top: 0;

  /* 중앙 라인 */
  &:before,
  &:after {
    content: '';
    position: absolute;
    width: 2px;
    height: 100%;
    background-color: #fff;
  }
  &:before {
    left: 50%;
    transform: translateX(-50%);
  }
  &:after {
    left: 0;
  }
  /* 오른쪽 라인 */
  &:nth-child(3) {
    &:before {
      left: auto;
      right: 50%;
      transform: translateX(50%);
    }
    &:after {
      left: auto;
      right: 0;
    }
  }
`;

const LeftPenaltyBox = styled.div`
  position: absolute;
  top: 50%;
  left: 0;
  transform: translate(0, -50%);
  width: 80px;
  height: 320px;
  border: 2px solid white;
`;

const RightPenaltyBox = styled.div`
  position: absolute;
  top: 50%;
  right: 0;
  transform: translate(0, -50%);
  width: 80px;
  height: 320px;
  border: 2px solid white;
`;
const GoalPost = styled.div`
  position: absolute;
  width: 10px;
  height: 200px;
  background-color: white;
  left: ${({ isLeft }) => (isLeft ? '0' : 'calc(100% - 10px)')};
  top: 50%;
  transform: translateY(-50%);
`;

const PenaltyBoxContent = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 60px;
  height: 150px;
  border: 2px solid white;
  background-color: transparent;
`;

const LeftPenaltyBoxContent = styled(PenaltyBoxContent)`
  left: 20px;
`;

const RightPenaltyBoxContent = styled(PenaltyBoxContent)`
  position: absolute;
  left: 60px;
`;
const Arc = styled.div`
  position: absolute;
  width: 95px;
  height: 80px;
  border: 2px solid #fff;
  border-radius: 50%;
  clip-path: ellipse(70% 80% at 50% 0%);
  background-color: transparent;
  ${({ isLeft }) =>
    isLeft ? 'left: 40px; top: 50%;' : 'right: 40px; top: 50%;'}
  ${({ isLeft }) =>
    isLeft ? 'transform: translate(0, -50%) rotate(90deg);' : 'transform: translate(0, -50%) rotate(-90deg);'}
  box-sizing: border-box;

  &:before,

  &:before {
    ${({ isLeft }) => (isLeft ? 'left: 50%;' : 'right: 50%;')}
    top: 0;
    transform: translate(-50%, -100%);
  }

  &:after {
    ${({ isLeft }) => (isLeft ? 'left: 50%;' : 'right: 50%;')}
    bottom: 0;
    transform: translate(-50%, 100%);
  }
`;