import React from "react";
import { Container } from "react-bootstrap";

const Parallax = () => {
  const textStyles = {
    background: "linear-gradient(to right, #4a90e2, #003366)", // Gradient from light blue to dark blue
    WebkitBackgroundClip: "text", // Clips the background to the text
    color: "transparent", // Makes the text itself transparent to show the gradient
    fontWeight: "bold", // Make the text bold
    textShadow: "1px 1px 2px rgba(0, 0, 0, 0.2)", // Slight shadow for brightness effect
  };

  return (
    <div className="parallax mb-5">
      <Container className="text-center px-5 py-5 justify-content-center">
        <div className="animated-texts bounceIn">
          <h1 style={textStyles}>
            Experience the Best hospitality at{" "}
            <span style={textStyles} className="hotel-color">
              lakeSide Hotel
            </span>
          </h1>
          <h3 style={textStyles}>
            We offer the best services for all your needs.
          </h3>
        </div>
      </Container>
    </div>
  );
};

export default Parallax;
