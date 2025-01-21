import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./hooks/context/authContext";
import { useState } from "react";
import Login from "./components/Login";
import Registro from "./components/Registro";
import DatosPersonales from "./components/DatosPersonales";
import DomicilioActual from "./components/DomicilioActual";
import Confirmacion from "./components/Confirmacion";
import Dashboard from "./components/Dashboard";
import "./App.css";

function App() {
  const [persona, setPersona] = useState({
    nombre: "",
    apPaterno: "",
    apMaterno: "",
    fechaNacimiento: "",
    lugarNacimiento: "",
    sexo: "",
    codigoPostal: "",
    pais: "",
    estado: "",
    municipio: "",
    calle: "",
    numeroInt: "",
    numeroExt: "",
  });

  const siguientePaso = (ruta) => {
    window.location.href = ruta;
  };

  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/registro" element={<Registro />} />
          {/* Flujo de creación de Persona */}
          <Route
            path="/persona/:usuarioId"
            element={
              <DatosPersonales
                persona={persona}
                setPersona={setPersona}
                siguientePaso={() => siguientePaso("/domicilio")}
              />
            }
          />
          <Route
            path="/domicilio"
            element={
              <DomicilioActual
                persona={persona}
                setPersona={setPersona}
                siguientePaso={() => siguientePaso("/confirmacion")}
              />
            }
          />
          <Route
            path="/confirmacion"
            element={<Confirmacion persona={persona} />}
          />
          <Route path="/dashboard" element={<Dashboard />} />
          {/* Redirección por defecto */}
          <Route path="*" element={<Login />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
