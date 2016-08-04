package br.org.ijv.cadastro.util.graficos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.org.ijv.cadastro.controller.PessoaBean;
import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.repository.Congregacoes;

@Named
@ViewScoped
public class MembrosCongregacoes extends PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LineChartModel areaSexo;
	
	@Inject
	private Congregacoes congregacoes;
	
	private LineChartModel animatedModelLinha;
	
	public LineChartModel getAnimatedModelLinha() {
		return animatedModelLinha;
	}
	
	@PostConstruct
	public void init() {		
		createAreaSexo();
	}
	
	public LineChartModel getAreaSexo() {
		return areaSexo;
	}
	
	private void createAreaSexo() {
		areaSexo = new LineChartModel();

		LineChartSeries feminino = new LineChartSeries();
		LineChartSeries masculino = new LineChartSeries();

		masculino.setFill(true);
		masculino.setLabel("Masculino");

		feminino.setFill(true);
		feminino.setLabel("Feminino");

		consultar();
		Congregacao congregacao = new Congregacao();
		for (int i = 0; i < congregacoes.todasCongregacoes().size(); i++) {
			congregacao = congregacoes.todasCongregacoes().get(i);
			masculino.set(congregacao.getNomeCongregacao(), pessoas.homemCongregacao(congregacao).size());
			feminino.set(congregacao.getNomeCongregacao(), pessoas.mulherCongregacao(congregacao).size());
		}

		areaSexo.addSeries(masculino);
		areaSexo.addSeries(feminino);

		areaSexo.setTitle("Gráfico de público masculino e feminino nas congregações");
		areaSexo.setAnimate(true);
		areaSexo.setLegendPosition("ne");
		areaSexo.setShowPointLabels(true);
		areaSexo.setStacked(true);
		areaSexo.setShowPointLabels(true);
		areaSexo.getAxes().put(AxisType.X, new CategoryAxis("Sexo"));
		Axis xAxis = new CategoryAxis("Congregações");
		areaSexo.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaSexo.getAxis(AxisType.Y);
		yAxis.setLabel("Membros");
		yAxis.setMin(0);
		yAxis.setMax(600);
		
	}
}
