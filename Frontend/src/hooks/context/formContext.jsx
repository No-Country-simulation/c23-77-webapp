import { createContext, useContext, useState } from "react";

const FormContext = createContext();

export const useForm = () => useContext(FormContext);

export const FormProvider = ({ children }) => {
  const [persona, setPersona] = useState({
    nombre: "",
    apPaterno: "",
    apMaterno: "",
    sexo: "",
    lugarNacimiento: "",
    fechaNacimiento: "",
    dni: "",
    usuarioId: null, // Relación opcional
    calle: "",
    municipio: "",
    estado: "",
    pais: "",
    codigoPostal: "",
    numeroInt: "",
    numeroExt: "",
  });

  const [paso, setPaso] = useState(1);

  return (
    <FormContext.Provider value={{ persona, setPersona, paso, setPaso }}>
      {children}
    </FormContext.Provider>
  );
};
