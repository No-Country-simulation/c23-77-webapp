import "./Login.css";
import loginImg from "../assets/login-img.png";
import { useState } from "react";
import api from "../api/api";
import { useAuth } from "../hooks/context/authContext"; // Asegúrate de usar el hook correcto

function Login() {
  const [login, setLogin] = useState({
    username: "",
    password: "",
  });

  const [error, setError] = useState(""); // Manejo de errores
  const { setIsAuth } = useAuth(); // Accede al contexto para actualizar la autenticación

  const cleanForm = () => {
    setLogin({
      username: "",
      password: "",
    });
    setError("");
  };

  const handleChange = (e) => {
    const { id, value } = e.target;
    setLogin({
      ...login,
      [id]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(""); // Resetea errores antes de intentar el login
    try {
      const response = await api.post("/login", {
        email: login.username,
        password: login.password,
      });

      const { jwTtoken } = response.data;

      // Guarda el token y establece la autenticación
      localStorage.setItem("token", jwTtoken);
      setIsAuth(true);

      cleanForm();
      window.location.href = "/dashboard"; // Redirige al dashboard tras iniciar sesión
    } catch (error) {
      console.error("Error durante el inicio de sesión:", error);
      setError("Credenciales inválidas. Por favor, inténtalo de nuevo."); // Maneja errores
    }
  };

  return (
    <div className="Login">
      <div className="form-container">
        <div className="form-content">
          <div className="text-container">
            <h2>Te damos la Bienvenida a</h2>
            <h1>Tu Banquito</h1>
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
            {error && (
              <div className="col-md-10 text-danger mt-2">
                <small>{error}</small>
              </div>
            )}
            <div className="col-12">
              <button type="submit" className="btn btn-primary mt-3 mb-3">
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
  );
}

export default Login;
