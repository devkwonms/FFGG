import React, { useEffect, useState } from 'react';
import styled from 'styled-components';

function Error({ error }) {
  const [ErrorText, setErrorText] = useState('해당 구단주는 존재하지 않습니다!');
  useEffect(() => {
    if (error.message !== 'Request failed with status code 404') {
      setErrorText('알 수 없는 오류가 발생했습니다. 다시 한번 검색해주세요');
    }
  }, [error]);
  return (
    <EContainer>
      <EWrapper>
        <TextContainer>
          <img
            src="https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/13ur/image/dMMFg4Edthw4Bh0uohu3VjISNCE.jpeg"
            alt="PORO"
            style={{ width: '360px' }}
          />
          <h2>{ErrorText}</h2>
        </TextContainer>
      </EWrapper>
    </EContainer>
  );
}

const EContainer = styled.div`
  max-width: 1140px;
  margin: 0 auto;
`;

const EWrapper = styled.div`
  width: 100%;
  padding-left: 0.46875rem;
  padding-right: 0.46875rem;
  margin-right: auto;
  margin-left: auto;
  margin: auto;
  padding: 2rem 0.445rem;
`;

const TextContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #e6e6e6;
  background-color: #171717;
  padding: 40px 0;
  flex-direction: column;
  color: #fff;
`;

export default Error;