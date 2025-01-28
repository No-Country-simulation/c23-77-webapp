import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./hooks/context/authContext";
import { FormProvider } from "./hooks/context/formContext";
import FormularioMultipaso from "./routes/FormularioMultipaso";
import Login from "./components/Login";
import Registro from "./components/Registro";
import Dashboard from "./components/Dashboard";
import Home from "./components/Home"; // Importa el componente Home
import ProtectedRoute from "./routes/ProtectedRoute";
import "./App.css";

function App() {
  return (
    <AuthProvider>
      <FormProvider>
        <Router>
          <Routes>
            <Route path="/" element={<Home />} /> {/* Ruta para Home */}
            <Route path="/login" element={<Login />} />
            <Route path="/registro" element={<Registro />} />
            <Route path="/formulario" element={<FormularioMultipaso />} />
            <Route
              path="/dashboard"
              element={
                <ProtectedRoute>
                  <Dashboard />
                </ProtectedRoute>
              }
            />
            <Route path="*" element={<Home />} />{" "}
            {/* Redirigir rutas no encontradas a Home */}
          </Routes>
        </Router>
      </FormProvider>
    </AuthProvider>
  );
}

export default App;
