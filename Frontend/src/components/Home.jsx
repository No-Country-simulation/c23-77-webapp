import "./Home.css";

function Home() {
  return (
    <div className="Home">
      {/* Header Section */}
      <header className="home-header">
        <div className="header-content">
          <h1>Título</h1>
          <p>Subtítulo</p>
          <button>Button</button>
        </div>
        <div className="header-navigation">
          <button className="nav-arrow">{"<"}</button>
          <button className="nav-arrow">{">"}</button>
        </div>
      </header>

      {/* Main Section */}
      <main className="home-main">
        <h2>Título</h2>
        <p>Subtítulo</p>
        <div className="card-container">
          <div className="card">
            <img
              src="https://via.placeholder.com/300x200" // Reemplazar con la URL de las imágenes
              alt="Imagen 1"
            />
            <div className="card-info">
              <h3>Título</h3>
              <p>Texto informativo</p>
            </div>
          </div>
          <div className="card">
            <img
              src="https://via.placeholder.com/300x200" // Reemplazar con la URL de las imágenes
              alt="Imagen 2"
            />
            <div className="card-info">
              <h3>Título</h3>
              <p>Texto informativo</p>
            </div>
          </div>
          <div className="card">
            <img
              src="https://via.placeholder.com/300x200" // Reemplazar con la URL de las imágenes
              alt="Imagen 3"
            />
            <div className="card-info">
              <h3>Título</h3>
              <p>Texto informativo</p>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Home;
