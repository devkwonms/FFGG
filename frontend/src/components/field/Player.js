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
      case 0: // GK
      return {
        top: '45%',
        left: '0%',
        backgroundColor: '#FFD700',
      };
    case 1: // SW
      return {
        top: '45%',
        left: '5%',
        backgroundColor: '#9C27B0',
      };
    case 2: // RWB
      return {
        top: '80%',
        left: '10%',
        backgroundColor: '#FFC107',
      };
    case 3: // RB
      return {
        top: '75%',
        left: '7.5%',
        backgroundColor: '#3F51B5',
      };
    case 4: // RCB
      return {
        top: '60%',
        left: '7.5%',
        backgroundColor: '#9C27B0',
      };
      // 수비
    case 5: // CB
      return {
        top: '45%',
        left: '7.5%',
        backgroundColor: '#9C27B0',
      };
    case 6: // LCB
      return {
        top: '30%',
        left: '7.5%',
        backgroundColor: '#9C27B0',
      };
    case 7: // LB
      return {
        top: '15%',
        left: '7.5%',
        backgroundColor: '#3F51B5',
      };
    case 8: // LWB
      return {
        top: '10%',
        left: '10%',
        backgroundColor: '#FFC107',
      };
      // 미드필더
    case 9: // RDM
      return {
        top: '60%',
        left: '15%',
        backgroundColor: '#9C27B0',
      };
    case 10: // CDM
      return {
        top: '45%',
        left: '15%',
        backgroundColor: '#3F51B5',
      };
    case 11: // LDM
      return {
        top: '30%',
        left: '15%',
        backgroundColor: '#2196F3',
      };
    case 12: // RM
      return {
        top: '75%',
        left: '22.5%',
        backgroundColor: '#FFC107',
      };
    case 13: // RCM
      return {
        top: '60%',
        left: '22.5%',
        backgroundColor: '#FFC107',
      };
    case 14: // CM
      return {
        top: '45%',
        left: '22.5%',
        backgroundColor: '#FFC107',
      };
    case 15: // LCM
      return {
        top: '30%',
        left: '22.5%',
        backgroundColor: '#FFC107',
      };
    case 16: // LM
      return {
        top: '15%',
        left: '22.5%',
        backgroundColor: '#FFC107',
      };
    case 17: // RAM
      return {
        top: '60%',
        left: '30%',
        backgroundColor: '#9C27B0',
      };
    case 18: // CAM
      return {
        top: '45%',
        left: '30%',
        backgroundColor: '#FFC107',
      };
    case 19: // LAM
      return {
        top: '30%',
        left: '30%',
        backgroundColor: '#2196F3',
      };
      // 공격수
    case 20: // RF
      return {
        top: '60%',
        left: '37.5%',
        backgroundColor: '#9C27B0',
      };
      case 21: // CF
      return {
        top: '45%',
        left: '37.5%',
        backgroundColor: '#9C27B0',
      };
      case 22: // LF
      return {
        top: '30%',
        left: '37.5%',
        backgroundColor: '#9C27B0',
      };
      case 23: // RW
      return {
        top: '75%',
        left: '37.5%',
        backgroundColor: '#9C27B0',
      };
      case 24: // RS
      return {
        top: '60%',
        left: '45%',
        backgroundColor: '#9C27B0',
      };
      case 25: // ST
      return {
        top: '45%',
        left: '45%',
        backgroundColor: '#9C27B0',
      };
      case 26: // LS
      return {
        top: '30%',
        left: '45%',
        backgroundColor: '#9C27B0',
      };
      case 27: // LW
      return {
        top: '15%',
        left: '37.5%',
        backgroundColor: '#9C27B0',
      };
      case 28: // SUB
      return {
        top: '20%',
        left: '25%',
        backgroundColor: '#9C27B0',
      };
      // default(오류 시)
      default:
        return {
          top: '0',
          left: '0',
          backgroundColor: '#000',
        };
    }
  };

  render() {
    const { spPosition, number, playerName, imageUrl} = this.props;

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
