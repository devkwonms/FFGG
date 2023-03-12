import styled from 'styled-components';

const SoccerField = () => {
  return (
    <Field>
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