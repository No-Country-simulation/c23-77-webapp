import { useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/context/authContext";

function Dashboard() {
  const { logout } = useAuth(); // Hook para manejar la autenticación
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login"); // Redirige al login después de cerrar sesión
  };

  return (
    <div className="Dashboard">
      <header className="dashboard-header">
        <h1>Bienvenido a Tu Banquito</h1>
        <button className="btn btn-danger" onClick={handleLogout}>
          Cerrar Sesión
        </button>
      </header>
      <main className="dashboard-content">
        <div className="dashboard-widgets">
          <div className="widget">
            <h2>Balance General</h2>
            <p>$10,000.00</p>
          </div>
          <div className="widget">
            <h2>Últimas Transacciones</h2>
            <ul>
              <li>Transferencia: -$200.00</li>
              <li>Depósito: +$500.00</li>
              <li>Pago de Servicios: -$150.00</li>
            </ul>
          </div>
          <div className="widget">
            <h2>Opciones Rápidas</h2>
            <button
              className="btn btn-primary"
              onClick={() => navigate("/transferencias")}
            >
              Transferir Dinero
            </button>
            <button
              className="btn btn-secondary"
              onClick={() => navigate("/historial")}
            >
              Ver Historial
            </button>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Dashboard;
