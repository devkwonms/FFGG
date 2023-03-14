import React from 'react';
import styled from 'styled-components';

const PlayerContainer = styled.div`
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  font-weight: bold;
`;
const PlayerImage = styled.img`
  height: 50px;
  width: 50px;
  margin-bottom: 5px;
`;

class Player extends React.Component {
  getPositionStyle = () => {
    const { position } = this.props;

    switch (position) {
      case 0:
        return {
          top: '25%',
          left: '10%',
          backgroundColor: '#F44336', // 빨간색
        };
      case 1:
        return {
          top: '25%',
          left: '30%',
          backgroundColor: '#9C27B0', // 보라색
        };
      case 2:
        return {
          top: '25%',
          left: '50%',
          backgroundColor: '#3F51B5', // 파란색
        };
      case 3:
        return {
          top: '25%',
          left: '70%',
          backgroundColor: '#2196F3', // 연한 파란색
        };
      case 4:
        return {
          top: '25%',
          left: '90%',
          backgroundColor: '#4CAF50', // 초록색
        };
      // 나머지 case들도 구현하면 됩니다.
      default:
        return {
          top: '0',
          left: '0',
          backgroundColor: '#000',
        };
    }
  };

  render() {
    const { number, playerName, imageUrl } = this.props;

    return (
      <PlayerContainer style={this.getPositionStyle()}>
        <PlayerImage src={imageUrl} alt={`Player ${number}`} />
        <div>{playerName}</div>
        <div>{number}</div>
      </PlayerContainer>
    );
  }
}

export default Player;
