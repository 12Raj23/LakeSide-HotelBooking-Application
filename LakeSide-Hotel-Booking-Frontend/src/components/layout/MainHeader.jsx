import React from "react";
//BY RAJESH R
const MainHeader = () => {
  // Styles for background and overlay
  const headerStyles = {
    background: "linear-gradient(to bottom right, #4a90e2, #003366)", // Deep blue gradient
    color: "#ffffff", // White text color for readability
    position: "relative",
    height: "400px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    textAlign: "center",
  };

  // Overlay styles for darkened effect
  const overlayStyles = {
    backgroundColor: "rgba(0, 0, 0, 0.5)", // Dark transparent overlay
    position: "absolute",
    top: 0,
    left: 0,
    width: "100%",
    height: "100%",
    zIndex: 1,
  };

  // Text styles for animated text
  const textStyles = {
    position: "relative",
    zIndex: 2,
    color: "#f8e9a1", // Warm golden text color
    fontWeight: "bold",
    textShadow: "2px 2px 4px rgba(0, 0, 0, 0.3)", // Subtle shadow for depth
  };

  return (
    <header style={headerStyles}>
      <div style={overlayStyles}></div>
      <div style={textStyles}>
        <h1>
          Welcome to <span style={{ color: "#ffcc00" }}>LakeSide Hotel</span>
        </h1>
        <h4>Experience the Best Hospitality in Town</h4>
      </div>
    </header>
  );
};

export default MainHeader;
