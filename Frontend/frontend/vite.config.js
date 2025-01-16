import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    open: true,
  },//agregué esto para que abra el navegador aumaticamente despues de correr run dev -ani-
})
