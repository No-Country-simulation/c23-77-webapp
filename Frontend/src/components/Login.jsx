import "./Login.css";
import loginImg from "../assets/login-img.png";
import { useState } from "react";

function Login() {
  const [login, setLogin] = useState({
    username: "",
    password: "",
  });

  const cleanForm = () => {
    setLogin({
      username: "",
      password: "",
    });
  };

  const handleChange = (e) => {
    const { id, value } = e.target;
    setLogin({
      ...login,
      [id]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(login);
    cleanForm();
  };

  return (
    <>
      <div className="Login">
        <div className="form-container">
          <div className="form-content">
            <div className="text-container">
              <h2>Te damos la Bienvenida a</h2>
              <h1>Mi Banquito</h1>
            </div>
            <form className="col g-3" onSubmit={handleSubmit}>
              <div className="col-md-10">
                <label htmlFor="username" className="form-label fw-bold">
                  Ingresa tu información
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="username"
                  placeholder="Cuenta Clave"
                  value={login.username}
                  onChange={handleChange}
                />
              </div>
              <div className="col-md-10">
                <label htmlFor="password" className="form-label mt-2 fw-bold">
                  Contraseña
                </label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  value={login.password}
                  onChange={handleChange}
                />
              </div>
              <div className="col-12">
                <button
                  type="submit"
                  className="btn btn-primary mt-3 mb-3"
                  onClick={() => console.log("Botón cliqueado")}
                >
                  Iniciar sesión
                </button>
              </div>
            </form>
            <a href="#">Olvidé mi contraseña</a>
            <h3>¡Nunca compartas los datos confidenciales de tus cuentas!</h3>
          </div>
          <div className="img-login">
            <img src={loginImg} alt="" />
          </div>
        </div>
      </div>
    </>
  );
}

export default Login;
