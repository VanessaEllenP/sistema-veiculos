// Equipe: Vanessa Ellen Paixão, Aislan Silva, Icaro Dantas e Kaique Conceição

import logo from './logo.svg';
import './App.css';
import Formulario from './Formulario';
import Tabela from './Tabela';
import { useEffect, useState } from 'react';

function App() {
  
  const veiculo = {
    codigo: null,
    marca: "",
    modelo: "",
    ano: ""
  }

  const [btnCadastrar, setBtnCadastrar] = useState(true)
  const[veiculos, setVeiculos] = useState([])
  const[objVeiculo, setObjveiculo] = useState(veiculo)

  const aoDigitar = (e) => {
    setObjveiculo({...objVeiculo, [e.target.name]:e.target.value})
  }

  // Método cadastrar
  const cadastrar = () => {
    fetch("http://localhost:8080/cadastrar",{
      method:"post",
      body:JSON.stringify(objVeiculo),
      headers:{
        "Content-type":"application/json",
        "Accept":"application/json"
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      if (retorno_convertido.mensagem !==undefined) {
        alert(retorno_convertido.mensagem)
      } else {
        setVeiculos([...veiculos, retorno_convertido])
        alert("Produto cadastrado com sucesso!")
        limparFormulario()
      }
    })
  }

  // Remover veículo
  const remover = () => {
    fetch("http://localhost:8080/remover/"+objVeiculo.codigo ,{
      method:"delete",
      headers:{
        "Content-type":"application/json",
        "Accept":"application/json"
      }
    })
    .then(retorno =>retorno.json())
    .then(retorno_convertido => {
      
      // Mensagem
      alert(retorno_convertido.mensagem)

      // Cópia do vetor veículos
      let vetorTemp = [...veiculos]

      // Indice
      let indice = vetorTemp.findIndex((p) => {
        return p.codigo===objVeiculo.codigo
      })

      // Remover veículo do vetor
      vetorTemp.splice(indice, 1)

      // Atualizar o vetor de veículos
      setVeiculos(vetorTemp)

      // Limpar formulário
      limparFormulario()
    })
  }

  // Alterar Veículo
  const alterar = () => {
    fetch("http://localhost:8080/alterar" ,{
      method:"put",
      body:JSON.stringify(objVeiculo),
      headers:{
        "Content-type":"application/json",
        "Accept":"application/json"
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      if (retorno_convertido.mensagem !== undefined) {
        alert(retorno_convertido.mensagem)
      } else {
        
        // Mensagem
        alert("Veículo alterado com sucesso!")

        // Cópia do vetor veículos
        let vetorTemp = [...veiculos]

        // Indice
        let indice = vetorTemp.findIndex((p)=> {
          return p.codigo===objVeiculo.codigo
        })

        // Alterar veículo do vetor
        vetorTemp[indice] = objVeiculo

        // Atualizar o vetor de veículos
        setVeiculos(vetorTemp)

        // Limpar Formulário
        limparFormulario()
      }
    })
  }

  // Limpar Formulário
  const limparFormulario = () => {
    setObjveiculo(veiculo)
    setBtnCadastrar(true)
  }

  // Selecionar veículo
  const selecionarVeiculo =(indice) => {
    setObjveiculo(veiculos[indice])
    setBtnCadastrar(false)
  }

  // Método para listar
  useEffect(() => {
    fetch("http://localhost:8080/listar")
    .then(retorno => retorno.json())
    .then(retorno_convertido => setVeiculos(retorno_convertido))
  },[])

  return (
    <div>
      {/* <p>{JSON.stringify(objVeiculo)}</p> */}
      <Formulario botao = {btnCadastrar} eventoTeclado={aoDigitar} cadastrar={cadastrar} obj={objVeiculo} cancelar={limparFormulario} remover={remover} alterar={alterar}/>
      <Tabela vetor={veiculos} selecionar = {selecionarVeiculo}/>
    </div>
  );
}

export default App;
