// import React from "react";
// import { Container } from "react-bootstrap";

// const Footer = () => {
//   let today = new Date();
//   return (
//     <footer className="by-dark text-light py-3 footer mt-lg-5">
//       <Container>
//         <Row>
//           <col xs={12} md={12} className="text-center">
//             <p> &copy;{today.getFullYear()} lakeside Hotel</p>
//           </col>
//         </Row>
//       </Container>
//     </footer>
//   );
// };

// export default Footer;

import React from "react";
import { Col, Container, Row } from "react-bootstrap";

const Footer = () => {
  let today = new Date();
  return (
    <footer className="bg-dark text-light py-3 footer mt-lg-5">
      <Container>
        <Row>
          <Col xs={12} md={12} className="text-center">
            <p className="mb-0">
              {" "}
              &copy; {today.getFullYear()} lakeSide Hotel BY Rajesh R
            </p>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
