/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao_BD;
import Model.ProdutoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ander
 */
public class ProdutoDao {
    
       //Método para visualizar todos Produtos;
     public List<ProdutoModel> visualizarClientes(){
        //Criando uma Connection com Classe Conexao_BD; 
        Connection conn=Conexao_BD.getConnection();
     
        //ArrayList de Produto;
         ArrayList<ProdutoModel> listProduto = new ArrayList<>();
        
         try {
             
             //Comando para selecionar toda tabela Produto;
             String sql="SELECT * FROM Produto";
             
             PreparedStatement patm = conn.prepareStatement(sql);
             //Executar;
             ResultSet rst=patm.executeQuery();
             
             while (rst.next()) {
               
                 //Instânciando  classe ProdutoModel;
                 ProdutoModel prod = new ProdutoModel();
                 //Setando os Valores;
                prod.setCodProd(rst.getString("codProd"));
                prod.setNome(rst.getString("nome"));
                prod.setDescricao(rst.getString("descricao"));
                prod.setValorUnit(rst.getDouble("valorUnit"));
                prod.setEstoque(rst.getInt("estoque"));
                 //Adicionando na Lista;
                listProduto.add(prod);
          
             }
             
             //Fechando conexão ResultSet;
             rst.close();
             
             //Fechando conexão PreparedStatement;
            patm.close();
            
            //Fechando conexão Connection;
            conn.close();
             
         } catch (Exception e) {
             //Algo de error mostrar essa mensagem;
             JOptionPane.showMessageDialog(null, "Error ao Visualizar Todos Produtos!");
         }
         //Retornando uma Lista de Produto;
        return listProduto;
         
     }
    
      //Método para adicionar produto ao banco de dados;
    public void adicionarProduto(ProdutoModel produto){
        
        //Criando uma Connection com Classe Conexao_BD; 
        Connection conn=Conexao_BD.getConnection();
        
       
        try {
            //Comando para inserir os dados dos produtos no banco de dados;
            String sql="insert into produto(codProd,nome,descricao,valorUnit,estoque)values(?,?,?,?,?)";
             
            PreparedStatement patm = conn.prepareStatement(sql);
            //Passando como paramentros os atributos do produto da classe ProdutoModel;
            patm.setString(1, produto.getCodProd());
            patm.setString(2, produto.getNome());
            patm.setString(3, produto.getDescricao());
            patm.setDouble(4, produto.getValorUnit());
            patm.setInt(5, produto.getEstoque());
            
            //Executar;
            int upd=patm.executeUpdate();
            
            if(upd>0){
                //Caso de tudo certo exibir essa mensagem;
                JOptionPane.showMessageDialog(null, "Produto Adicionado Com Sucesso");
            }else{
                //Caso de error  exibir essa mensagem;
                JOptionPane.showMessageDialog(null, "Produto Não Adicionado !","Error ",JOptionPane.ERROR_MESSAGE);
            }
            
             //Fechando conexão PreparedStatement;
            patm.close();
            
            //Fechando conexão Connection;
            conn.close();
            
        } catch (SQLException ex) {
            //Caso aconteça algum error mostrar essa mensagem;
           JOptionPane.showMessageDialog(null, "Error ao Adicionar Produto  !");
        }
        
    }
    
     //Método para fazer atualização dos dados dos Prodtos;
       public void atualizarProdtuo(ProdutoModel produto){  
           //Criando uma Connection com Classe Conexao_BD; 
        Connection conn=Conexao_BD.getConnection();
               try {
           
          //Comando para que realizar atualização no banco de dados;
            String sql="UPDATE produto SET nome=?,descricao=?, valorUnit=?, estoque=? WHERE codProd=?";
            
            
            
            PreparedStatement patm = conn.prepareStatement(sql);
            //Passandoos valores nos paramentros;
            patm.setString(1,produto.getNome());
            patm.setString(2,produto.getDescricao());
            patm.setDouble(3,produto.getValorUnit());
            patm.setInt(4,produto.getEstoque());
            patm.setString(5, produto.getCodProd());
            //Executar;
             int res= patm.executeUpdate();
            
            if(res>0){
                //Mensagem para mostrar para usuário caso esteja tudo correto!;
                JOptionPane.showMessageDialog(null,"Produto Atualizador com Sucesso !","Sucesso!",JOptionPane.INFORMATION_MESSAGE);
            }else{
                //Mensagem oara exibir para usuário caso tenha informações incorretas;
                JOptionPane.showMessageDialog(null,"Produto não Atualizador !","Error",JOptionPane.ERROR_MESSAGE);
            }
            
            //Fechando conexão PreparedStatement;
            patm.close();
            
            //Fechando conexão Connection;
            conn.close();
            
        } catch (Exception e) {
            //Mensagem caso de error;
              JOptionPane.showMessageDialog(null,"Error ao Atualizar Produto !","Error",JOptionPane.ERROR_MESSAGE);
        }
     
       }
    
       
        //Método para deletar Produto no banco de dados pelo código do Produto;
      public void deletaProduto(ProdutoModel model){
          
           //Criando uma Connection com Classe Conexao_BD; 
        Connection conn=Conexao_BD.getConnection();
             
        try {

          // Comando que  Deletar Produto pelo código;
            String sql = "DELETE FROM produto WHERE codProd=?";

            PreparedStatement patm = conn.prepareStatement(sql);
             
            //Passando em paramentros código do Produto;
            patm.setString(1, model.getCodProd());
            
            //Executar;
            int res = patm.executeUpdate();

            if (res > 0) {
                //Caso de tudo certo será exibido essa mensagem para usuário;
                JOptionPane.showMessageDialog(null, "Produto Deletado com Sucesso !", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                 //Caso de errado será exibido essa mensagem para usuário;
                JOptionPane.showMessageDialog(null, "Produto não Deletado !", "Error", JOptionPane.ERROR_MESSAGE);
            }

            //Fechando conexão PreparedStatement;
            patm.close();
            
            //Fechando conexão Connection;
            conn.close();
            

        } catch (Exception e) {
            //Caso de error ao deletar mostrar essa mensagem;
             JOptionPane.showMessageDialog(null, "Error ao Deletar Produto  !", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
      }
}
