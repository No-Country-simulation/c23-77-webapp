/***********************************************
 * src/routes/ProtectedRoute.jsx
 ***********************************************/

import { Navigate, Outlet } from "react-router-dom";
import useAuth from "../hooks/useAuth";

const ProtectedRoute = () => {
  const { isAuth, loading } = useAuth();

  // Mostrar un Spinner o mensaje de carga mientras se verifica la autenticación
  if (loading) {
    return <div>Cargando...</div>;
  }

  // Redirigir al login si no está autenticado, o renderizar el contenido protegido
  return isAuth ? <Outlet /> : <Navigate to="/login" />;
};

export default ProtectedRoute;
