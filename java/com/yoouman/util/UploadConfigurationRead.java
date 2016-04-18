package com.yoouman.util;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
/**
 * ��̬��ȡ�����ļ���
 * 
 * @author LF
 *
 * @Time 2013-11-24����08:25:22
 */
public class UploadConfigurationRead {

	/**
	 * �����ļ�ȫ��,��Ҫ��ʱ������������PFILE
	 */
	private static String PFILE = "yoouman.properties";

	/**
	 * �����ļ�·��
	 */
	private URI uri = null;

	/**
	 * �����ļ����Ӧ�����Զ������
	 */
	private long m_lastModifiedTime = 0;

	/**
	 * ��Ӧ�������ļ����ļ��������
	 */
	private File m_file = null;

	/**
	 * �����ļ����Ӧ�����Զ������
	 */
	private Properties m_props = null;

	/**
	 * Ψһʵ��
	 */
	private static UploadConfigurationRead m_instance = new UploadConfigurationRead();

	/**
	 * ˽�й��캯��
	 * 
	 * @throws URISyntaxException
	 */
	private UploadConfigurationRead() {
		try {
			m_lastModifiedTime = getFile().lastModified();
			if (m_lastModifiedTime == 0) {
				System.err.println(PFILE + "file does not exist!");
			}
			m_props = new Properties();
			m_props.load(new FileInputStream(getFile()));

		} catch (URISyntaxException e) {
			System.err.println(PFILE+"�ļ�·������ȷ");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(PFILE+"�ļ���ȡ�쳣");
			e.printStackTrace();
		}
	}

	/**
	 * ����ClassPath·����ȡ�ļ�
	 * 
	 * @return File����
	 * @throws URISyntaxException
	 */

	private File getFile() throws URISyntaxException {
		URI fileUri = this.getClass().getClassLoader().getResource(PFILE).toURI();
		m_file = new File(fileUri);
		return m_file;
	}

	/**
	 * ��̬��������
	 * 
	 * @return ����ConfigurationRead�ĵ�һʵ��
	 */
	public synchronized static UploadConfigurationRead getInstance() {
		return m_instance;
	}

	/**
	 * ��ȡһ�ض���������
	 */
	public String getConfigItem(String name, String defaultVal) {
		long newTime = m_file.lastModified();
		// ��������ļ��Ƿ��޸�
		if (newTime == 0) {
			// �����ļ�������
			if (m_lastModifiedTime == 0) {
				System.err.println(PFILE + " file does not exist!");
			} else {
				System.err.println(PFILE + " file was deleted!!");
			}
			return defaultVal;
		} else if (newTime > m_lastModifiedTime) {
			m_props.clear();
			try {
				m_props.load(new FileInputStream(getFile()));
			} catch (Exception e) {
				System.err.println("�ļ����¶�ȡ�쳣");
				e.printStackTrace();
			}
		}
		m_lastModifiedTime = newTime;
		String val = m_props.getProperty(name);
		if (val == null) {
			return defaultVal;
		} else {
			return val;
		}
	}

	/**
	 * ��ȡһ�ض���������
	 * 
	 * @param name
	 *            �����������
	 * @return �������ֵ���������ڣ��� �գ��������ڣ�
	 */
	public String getConfigItem(String name) {
		return getConfigItem(name, "");
	}

}
