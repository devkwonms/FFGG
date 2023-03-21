import styled from 'styled-components';

const Goal = () => {
  return (
    <StyledGoal>
      <div className="net" />
      <div className="post left-post" />
      <div className="post right-post" />
    </StyledGoal>
  );
};

const StyledGoal = styled.div`
  position: relative;
  width: 120px;
  height: 80px;

  .post {
    position: absolute;
    width: 10px;
    height: 80px;
    background-color: #fff;
  }

  .left-post {
    left: -10px;
  }

  .right-post {
    right: -10px;
  }

  .net {
    position: absolute;
    top: 10px;
    left: 0;
    right: 0;
    height: 60px;
    border-top: 2px solid #fff;
    border-bottom: 2px solid #fff;
  }
`;

export default Goal;
